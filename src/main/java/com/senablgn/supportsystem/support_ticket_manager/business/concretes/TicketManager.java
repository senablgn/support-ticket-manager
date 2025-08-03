package com.senablgn.supportsystem.support_ticket_manager.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.senablgn.supportsystem.support_ticket_manager.business.abstracts.TicketService;
import com.senablgn.supportsystem.support_ticket_manager.core.mappers.ModelMapperService;
import com.senablgn.supportsystem.support_ticket_manager.dataAccess.TicketRepository;
import com.senablgn.supportsystem.support_ticket_manager.dataAccess.UserRepository;
import com.senablgn.supportsystem.support_ticket_manager.dto.request.CreateTicketRequest;
import com.senablgn.supportsystem.support_ticket_manager.dto.response.TicketResponse;
import com.senablgn.supportsystem.support_ticket_manager.entities.Ticket;
import com.senablgn.supportsystem.support_ticket_manager.entities.TicketStatus;
import com.senablgn.supportsystem.support_ticket_manager.entities.User;
import com.senablgn.supportsystem.support_ticket_manager.exceptions.BaseException;
import com.senablgn.supportsystem.support_ticket_manager.exceptions.ErrorMessage;
import com.senablgn.supportsystem.support_ticket_manager.exceptions.MessageType;
import com.senablgn.supportsystem.support_ticket_manager.security.CustomUserDetails;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TicketManager implements TicketService {
	private TicketRepository ticketRepository;
	private ModelMapperService modelMapperService;
	private UserRepository userRepository;

	@Override
	public List<TicketResponse> getAllTickets() {
		List<Ticket> tickets = this.ticketRepository.findAll();
		List<TicketResponse> ticketResponse = tickets.stream()
				.map(ticket -> modelMapperService.forResponse().map(ticket, TicketResponse.class))
				.collect(Collectors.toList());
		return ticketResponse;
	}

	@Override
	public TicketResponse getTicketById(Long id) {
		Ticket ticket = this.ticketRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.TICKET_NOT_FOUND, id.toString())));
		TicketResponse ticketResponse = this.modelMapperService.forResponse().map(ticket, TicketResponse.class);
		return ticketResponse;
	}

	@Override
	public TicketResponse createTicket(CreateTicketRequest createTicketRequest) {
		Ticket ticket = this.modelMapperService.forRequest().map(createTicketRequest, Ticket.class);
		CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		User user = this.userRepository.findById(currentUser.getId()).orElseThrow(
				() -> new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, currentUser.getId().toString())));
		ticket.setStatus(TicketStatus.PENDING);
		ticket.setCreatedAt(LocalDateTime.now());
		ticket.setUser(user);
		this.ticketRepository.save(ticket);
		TicketResponse ticketResponse = this.modelMapperService.forResponse().map(ticket, TicketResponse.class);
		return ticketResponse;
	}

	@Override
	public List<TicketResponse> getTicketsByUserId() {
		CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Ticket> tickets = this.ticketRepository.findByUserId(currentUser.getId());
		List<TicketResponse> ticketResponse = tickets.stream()
				.map(ticket -> modelMapperService.forResponse().map(ticket, TicketResponse.class))
				.collect(Collectors.toList());
		return ticketResponse;
	}
}
