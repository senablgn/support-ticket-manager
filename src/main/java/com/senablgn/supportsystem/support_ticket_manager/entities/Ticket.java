package com.senablgn.supportsystem.support_ticket_manager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subject;
	private String description;
	@Enumerated(EnumType.STRING)
	private TicketStatus status;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
