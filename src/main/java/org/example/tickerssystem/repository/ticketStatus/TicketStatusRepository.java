package org.example.tickerssystem.repository.ticketStatus;

import org.example.tickerssystem.entity.Ticket;
import org.example.tickerssystem.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TicketStatusRepository extends JpaRepository<TicketStatus, Long> {
    TicketStatus findByNameIgnoreCase(String name);
}
