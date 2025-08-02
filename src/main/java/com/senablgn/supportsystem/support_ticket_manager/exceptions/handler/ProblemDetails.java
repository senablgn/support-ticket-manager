package com.senablgn.supportsystem.support_ticket_manager.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDetails {
	ExceptionInfo exceptionInfo;
	private int ststus;
}
