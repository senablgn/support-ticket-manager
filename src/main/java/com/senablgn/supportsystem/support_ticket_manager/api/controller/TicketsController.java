package com.senablgn.supportsystem.support_ticket_manager.api.controller;

import com.senablgn.supportsystem.support_ticket_manager.business.abstracts.TicketService;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.DataResult;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.SuccessDataResult;
import com.senablgn.supportsystem.support_ticket_manager.core.utilities.results.SuccessResult;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.CreateTicketRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.TicketResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketsController {
	private TicketService ticketService;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DataResult<List<TicketResponse>>>getAllTickets(){
		return ResponseEntity.ok(new SuccessDataResult("tickets", this.ticketService.getAllTickets()));
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or #id==authentication.principal.id")
	public ResponseEntity<DataResult>getTicketById(@PathVariable Long id){
		return ResponseEntity.ok(new SuccessDataResult("ticket found", this.ticketService.getTicketById(id)));
	}
	@PostMapping("/create")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<DataResult>createTicket(@RequestBody CreateTicketRequest createTicketRequest){
		return ResponseEntity.ok(new SuccessDataResult("ticket created", this.ticketService.createTicket(createTicketRequest)));
	}
}
