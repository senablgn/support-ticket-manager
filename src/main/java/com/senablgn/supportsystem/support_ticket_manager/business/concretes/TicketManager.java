package com.senablgn.supportsystem.support_ticket_manager.business.concretes;

import com.senablgn.supportsystem.support_ticket_manager.business.abstracts.TicketService;
import com.senablgn.supportsystem.support_ticket_manager.core.mappers.ModelMapperService;
import com.senablgn.supportsystem.support_ticket_manager.dataAccess.TicketRepository;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.TicketResponse;
import com.senablgn.supportsystem.support_ticket_manager.entities.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketManager implements TicketService {
	private TicketRepository ticketRepository;
	private ModelMapperService modelMapperService;
	@Override
	public List<TicketResponse> getAllTickets() {
		List<Ticket> tickets = this.ticketRepository.findAll();
		List<TicketResponse> ticketResponse = tickets.stream()
				.map(ticket -> modelMapperService.forResponse().map(ticket, TicketResponse.class))
				.collect(Collectors.toList());
		return ticketResponse;
	}
}
