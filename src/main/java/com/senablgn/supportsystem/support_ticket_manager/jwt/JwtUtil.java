package com.senablgn.supportsystem.support_ticket_manager.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

@Getter
@Component
public class JwtUtil {
	@Value("${SECRET_KEY}")
	private String jwtSecret;

	public String generateJwtToken(UserDetails userDetails) {
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.claim("roles", userDetails.getAuthorities())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
				.signWith(getJwtSecretKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public String generateRefreshToken(UserDetails userDetails) {
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
				.signWith(getJwtSecretKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public Key getJwtSecretKey() {
		byte[] decode = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(decode);
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getJwtSecretKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getJwtSecretKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getExpiration()
				.before(new Date());
	}
}
