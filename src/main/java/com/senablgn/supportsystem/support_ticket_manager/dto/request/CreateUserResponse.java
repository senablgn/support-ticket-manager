package com.senablgn.supportsystem.support_ticket_manager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserResponse {
	private String username;
	private String password;
	private String role;
}
