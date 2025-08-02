package com.senablgn.supportsystem.support_ticket_manager.exceptions;

public class BaseException extends RuntimeException{
	public BaseException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
	}
}
