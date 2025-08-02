package com.senablgn.supportsystem.support_ticket_manager.api.controller;

import com.senablgn.supportsystem.support_ticket_manager.business.abstracts.TicketService;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.DataResult;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.SuccessDataResult;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.SuccessResult;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.TicketResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketsController {
	private TicketService ticketService;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DataResult<List<TicketResponse>>>getAllTickets(){
		return ResponseEntity.ok(new SuccessDataResult("tickets", this.ticketService.getAllTickets()));
	}
}
