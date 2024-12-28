package org.example.tickerssystem.service.ticket;

import org.example.tickerssystem.entity.Customer;
import org.example.tickerssystem.entity.Event;
import org.example.tickerssystem.entity.Ticket;

import java.util.List;

public interface TicketService {
    void saveAll(TicketPackDTO ticketPack, Event event);
    List<Ticket> findAll();
    List<Ticket> findAllFreeTicketsByEventId(Long eventId);
    void sellTicket(Ticket ticket, Customer customer);
    void deleteAll();
}