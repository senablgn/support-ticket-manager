package com.senablgn.supportsystem.support_ticket_manager.dto.response;

import com.senablgn.supportsystem.support_ticket_manager.entities.TicketStatus;
import com.senablgn.supportsystem.support_ticket_manager.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
	private String subject;
	private String description;
	private TicketStatus status;
	private LocalDateTime createdAt;
	private Long userId;
	private String username;
}
