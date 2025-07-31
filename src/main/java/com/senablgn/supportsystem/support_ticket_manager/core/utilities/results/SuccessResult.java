package com.senablgn.supportsystem.support_ticket_manager.core.utilities.results;

public class SuccessResult extends Result {

	public SuccessResult() {
		super(true);
	}

	public SuccessResult(String message) {
		super(true, message);
	}
}
