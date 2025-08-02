package com.senablgn.supportsystem.support_ticket_manager.dataAccess;

import com.senablgn.supportsystem.support_ticket_manager.entities.BlackListedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListedTokenRepository extends JpaRepository<BlackListedToken, Long> {
	boolean existsByToken(String token);
}
