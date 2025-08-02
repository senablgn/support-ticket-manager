package com.senablgn.supportsystem.support_ticket_manager.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ErrorMessage {
	private MessageType type;
	private String details;
	public String getMessage(){
		StringBuilder builder = new StringBuilder();
		builder.append(type.getMessage());
		if(details != null){
			builder.append(": "+details);
		}
		return builder.toString();
	}
}
