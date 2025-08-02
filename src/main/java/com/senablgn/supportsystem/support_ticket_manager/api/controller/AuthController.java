package com.senablgn.supportsystem.support_ticket_manager.api.controller;

import com.senablgn.supportsystem.support_ticket_manager.business.abstracts.AuthService;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.DataResult;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.SuccessDataResult;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.CreateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<DataResult>register(@RequestBody CreateUserRequest createUserRequest) {
		return ResponseEntity.ok(new SuccessDataResult("user registered", authService.register(createUserRequest)));
	}
}
