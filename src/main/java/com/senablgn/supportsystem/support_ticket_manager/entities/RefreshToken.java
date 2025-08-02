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
@Table(name = "refresh_tokens")
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	@Column(name = "expiry_date")
	private Date expiryDate;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
