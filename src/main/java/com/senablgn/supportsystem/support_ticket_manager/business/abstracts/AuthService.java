package com.senablgn.supportsystem.support_ticket_manager.business.abstracts;

import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.DataResult;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.AuthRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.CreateUserRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.RefreshTokenRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.AuthResponse;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.UserResponse;

public interface AuthService {
	UserResponse register(CreateUserRequest createUserRequest);
	AuthResponse login(AuthRequest authRequest);
	AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
