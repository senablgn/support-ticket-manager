package com.senablgn.supportsystem.support_ticket_manager.business.abstracts;

import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.DataResult;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.CreateUserRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.UserResponse;

public interface AuthService {
	UserResponse register(CreateUserRequest createUserRequest);
}
