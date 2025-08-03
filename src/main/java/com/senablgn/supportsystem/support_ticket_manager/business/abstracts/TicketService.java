package com.senablgn.supportsystem.support_ticket_manager.business.abstracts;

import com.senablgn.supportsystem.support_ticket_manager.dto.request.CreateTicketRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.TicketResponse;

import java.util.List;

public interface TicketService {
	List<TicketResponse> getAllTickets();
	TicketResponse getTicketById(Long id);
	TicketResponse createTicket(CreateTicketRequest createTicketRequest);
	List<TicketResponse> getTicketsByUserId();
}
