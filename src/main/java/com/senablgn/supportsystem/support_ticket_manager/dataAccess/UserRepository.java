package com.senablgn.supportsystem.support_ticket_manager.dataAccess;

import com.senablgn.supportsystem.support_ticket_manager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
