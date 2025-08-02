package com.senablgn.supportsystem.support_ticket_manager.business.concretes;

import com.senablgn.supportsystem.support_ticket_manager.business.abstracts.AuthService;
import com.senablgn.supportsystem.support_ticket_manager.core.mappers.ModelMapperService;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.DataResult;
import com.senablgn.supportsystem.support_ticket_manager.dataAccess.UserRepository;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.CreateUserRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.UserResponse;
import com.senablgn.supportsystem.support_ticket_manager.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthManager implements AuthService {
	private ModelMapperService modelMapperService;
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public UserResponse register(CreateUserRequest createUserRequest) {
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
		user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
		user.setRole("USER");
		User savedUser = this.userRepository.save(user);
		UserResponse userResponse = this.modelMapperService.forResponse().map(savedUser, UserResponse.class);
		return userResponse;
	}
}
