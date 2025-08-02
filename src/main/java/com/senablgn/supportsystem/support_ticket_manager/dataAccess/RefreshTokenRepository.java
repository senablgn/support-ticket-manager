package com.senablgn.supportsystem.support_ticket_manager.dataAccess;

import com.senablgn.supportsystem.support_ticket_manager.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
