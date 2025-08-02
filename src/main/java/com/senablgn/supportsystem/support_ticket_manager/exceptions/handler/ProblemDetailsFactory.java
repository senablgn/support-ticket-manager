package com.senablgn.supportsystem.support_ticket_manager.exceptions.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ProblemDetailsFactory<E> {
	public ProblemDetails generateProblemDetailsFactory(HttpServletRequest request, Object message)
			throws UnknownHostException {
		ExceptionInfo exceptionInfo = new ExceptionInfo();
		exceptionInfo.setMessage(message);
		exceptionInfo.setDate(new Date());
		exceptionInfo.setPath(request.getRequestURI());
		exceptionInfo.setHost(getHost());
		ProblemDetails problemDetails = new ProblemDetails(exceptionInfo, HttpStatus.BAD_REQUEST.value());
		return problemDetails;
	}

	private String getHost() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress() + ":" + InetAddress.getLocalHost().getHostName();
	}
}
