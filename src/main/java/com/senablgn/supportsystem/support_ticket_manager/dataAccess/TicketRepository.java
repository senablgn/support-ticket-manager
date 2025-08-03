package com.senablgn.supportsystem.support_ticket_manager.dataAccess;

import com.senablgn.supportsystem.support_ticket_manager.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
	List<Ticket>findByUserId(Long id);
}
