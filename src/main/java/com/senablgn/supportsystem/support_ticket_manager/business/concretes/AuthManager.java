package com.senablgn.supportsystem.support_ticket_manager.business.concretes;

import com.senablgn.supportsystem.support_ticket_manager.business.abstracts.AuthService;
import com.senablgn.supportsystem.support_ticket_manager.core.mappers.ModelMapperService;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.DataResult;
import com.senablgn.supportsystem.support_ticket_manager.dataAccess.UserRepository;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.AuthRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.CreateUserRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.AuthResponse;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.UserResponse;
import com.senablgn.supportsystem.support_ticket_manager.entities.User;
import com.senablgn.supportsystem.support_ticket_manager.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthManager implements AuthService {
	private ModelMapperService modelMapperService;
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private JwtUtil jwtUtil;
	private AuthenticationManager  authenticationManager;
	private UserDetailsService userDetailsService;
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
//		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//		UserDetails userDetails = this.userDetailsService.loadUserByUsername(authRequest.getUsername());
//		String accessToken = this.jwtUtil.generateJwtToken(userDetails);
//		return new AuthResponse(accessToken);
		return null;
	}
}
