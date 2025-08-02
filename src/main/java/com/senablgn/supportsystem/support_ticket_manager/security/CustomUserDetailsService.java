package com.senablgn.supportsystem.support_ticket_manager.security;

import com.senablgn.supportsystem.support_ticket_manager.dataAccess.UserRepository;
import com.senablgn.supportsystem.support_ticket_manager.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username).orElseThrow();
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole().replaceFirst("_ROLE",""))
				.build();
	}
}
