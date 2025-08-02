package com.senablgn.supportsystem.support_ticket_manager.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum MessageType {
	GENERAL_ERROR("1","General error has occured"),
	TOKEN_NOT_FOUND("2","Token not found"),
	MISSING_TOKEN("3","Token is missing"),
	INVALID_TOKEN("4","Token is already invalid"),
	USER_NOT_FOUND("5","User not found"),
	TICKET_NOT_FOUND("6","Ticket not found");
	private String code;
	private String message;
}
