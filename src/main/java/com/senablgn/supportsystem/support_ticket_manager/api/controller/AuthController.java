package com.senablgn.supportsystem.support_ticket_manager.api.controller;

import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.Result;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.SuccessResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senablgn.supportsystem.support_ticket_manager.business.abstracts.AuthService;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.DataResult;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.SuccessDataResult;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.AuthRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.CreateUserRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.RefreshTokenRequest;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<DataResult> register(@RequestBody CreateUserRequest createUserRequest) {
		return ResponseEntity
				.ok(new SuccessDataResult("user registered", this.authService.register(createUserRequest)));
	}

	@PostMapping("/login")
	public ResponseEntity<DataResult> login(@RequestBody AuthRequest authRequest) {
		return ResponseEntity.ok(new SuccessDataResult("user login", this.authService.login(authRequest)));
	}

	@PostMapping("/refreshToken")
	public ResponseEntity<DataResult> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		return ResponseEntity
				.ok(new SuccessDataResult("user refresh token", this.authService.refreshToken(refreshTokenRequest)));
	}

	@PostMapping("/logout")
	public ResponseEntity<Result> logout(@RequestHeader(value = "Authorization") String header) {
		this.authService.logout(header);
		return ResponseEntity.ok(new SuccessResult("user logged out"));
	}
}
