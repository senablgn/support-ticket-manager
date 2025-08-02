package com.senablgn.supportsystem.support_ticket_manager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "black_listed_tokens")
public class BlackListedToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	@Column(name = "expires_at")
	private Date expiresAt;
	@Column(name = "black_listed_at")
	private Date blackListedAt;
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
}
