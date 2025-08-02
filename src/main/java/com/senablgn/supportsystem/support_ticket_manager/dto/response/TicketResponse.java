package com.senablgn.supportsystem.support_ticket_manager.dto.response;

import com.senablgn.supportsystem.support_ticket_manager.entities.TicketStatus;
import com.senablgn.supportsystem.support_ticket_manager.entities.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class TicketResponse {
	private String subject;
	private String description;
	private TicketStatus status;
	private LocalDateTime createdAt;
	private User user;
}
