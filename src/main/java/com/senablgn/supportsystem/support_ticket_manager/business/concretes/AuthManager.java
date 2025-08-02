package com.senablgn.supportsystem.support_ticket_manager.business.concretes;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.senablgn.supportsystem.support_ticket_manager.business.abstracts.AuthService;
import com.senablgn.supportsystem.support_ticket_manager.core.mappers.ModelMapperService;
import com.senablgn.supportsystem.support_ticket_manager.dataAccess.RefreshTokenRepository;
import com.senablgn.supportsystem.support_ticket_manager.dataAccess.UserRepository;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.AuthRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.CreateUserRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.RefreshTokenRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.AuthResponse;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.UserResponse;
import com.senablgn.supportsystem.support_ticket_manager.entities.RefreshToken;
import com.senablgn.supportsystem.support_ticket_manager.entities.User;
import com.senablgn.supportsystem.support_ticket_manager.exceptions.BaseException;
import com.senablgn.supportsystem.support_ticket_manager.exceptions.ErrorMessage;
import com.senablgn.supportsystem.support_ticket_manager.exceptions.MessageType;
import com.senablgn.supportsystem.support_ticket_manager.jwt.JwtUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthManager implements AuthService {
	private ModelMapperService modelMapperService;
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private JwtUtil jwtUtil;
	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;
	private RefreshTokenRepository refreshTokenRepository;

	@Override
	public UserResponse register(CreateUserRequest createUserRequest) {
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
		user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
		user.setRole("USER");
		User savedUser = this.userRepository.save(user);
		UserResponse userResponse = this.modelMapperService.forResponse().map(savedUser, UserResponse.class);
		return userResponse;
	}

	@Override
	public AuthResponse login(AuthRequest authRequest) {
		Authentication authenticate = this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
		String accessToken = this.jwtUtil.generateJwtToken(userDetails);
		String refreshToken = this.jwtUtil.generateRefreshToken(userDetails);
		User user = this.userRepository.findByUsername(authRequest.getUsername()).orElseThrow();
		RefreshToken savedRefreshToken = new RefreshToken();
		savedRefreshToken.setToken(refreshToken);
		savedRefreshToken.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7));
		savedRefreshToken.setUser(user);
		this.refreshTokenRepository.save(savedRefreshToken);
		return new AuthResponse(accessToken, refreshToken);
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		RefreshToken refreshToken = this.refreshTokenRepository.findByToken(refreshTokenRequest.getRefreshToken())
				.orElseThrow(() -> new BaseException(
						new ErrorMessage(MessageType.GENERAL_ERROR, refreshTokenRequest.getRefreshToken())));
		if (refreshToken.getExpiryDate().before(new Date())) {
			throw new RuntimeException("Refresh token expired");
		}
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(refreshToken.getUser().getUsername());
		String accessToken = this.jwtUtil.generateJwtToken(userDetails);
		String newRefreshToken = this.jwtUtil.generateRefreshToken(userDetails);
		refreshToken.setToken(newRefreshToken);
		refreshToken.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 7));
		this.refreshTokenRepository.save(refreshToken);
		return new AuthResponse(accessToken, newRefreshToken);
	}
}
