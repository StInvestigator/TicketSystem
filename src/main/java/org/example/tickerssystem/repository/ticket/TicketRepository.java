package org.example.tickerssystem.repository.ticket;

import org.example.tickerssystem.entity.Ticket;
import org.example.tickerssystem.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findTicketsByStatus_NameAndEvent_Id(String status_name, Long event_id);
}
