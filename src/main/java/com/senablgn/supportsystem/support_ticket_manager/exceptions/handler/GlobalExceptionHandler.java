package com.senablgn.supportsystem.support_ticket_manager.exceptions.handler;

import com.senablgn.supportsystem.support_ticket_manager.exceptions.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.UnknownHostException;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
	private ProblemDetailsFactory problemDetailsFactory;
	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ProblemDetails> handleException(BaseException exception, HttpServletRequest request)
			throws UnknownHostException {
		return ResponseEntity.badRequest().body(this.problemDetailsFactory.generateProblemDetailsFactory(request,exception.getMessage()));
	}
}
