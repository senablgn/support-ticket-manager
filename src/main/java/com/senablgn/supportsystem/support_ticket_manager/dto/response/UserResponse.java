package com.senablgn.supportsystem.support_ticket_manager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	private String username;
	private String password;
	private String role;
}
