package com.senablgn.supportsystem.support_ticket_manager.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum MessageType {
	GENERAL_ERROR("1","General error has occured");
	private String code;
	private String message;
}
