package org.example.tickerssystem.service.ticket;

import org.example.tickerssystem.entity.Customer;
import org.example.tickerssystem.entity.Event;
import org.example.tickerssystem.entity.Ticket;
import org.example.tickerssystem.repository.ticketStatus.TicketStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.tickerssystem.repository.ticket.TicketRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketStatusRepository statusRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveAll(TicketPackDTO ticketPack, Event event) {
        if (ticketPack == null || event == null || ticketPack.getCost().compareTo(BigDecimal.valueOf(0)) < 0 || ticketPack.getCount() < 1) {
            return;
        }
        List<Ticket> tickets = new ArrayList<>();
        int number = 0;
        if (event.getTickets() != null) number = event.getTickets()
                .stream().max(Comparator.comparingInt(Ticket::getNumber))
                .map(Ticket::getNumber).orElse(0);
        for (int i = 1; i <= ticketPack.getCount(); i++) {
            Ticket ticket = new Ticket();
            ticket.setCost(ticketPack.getCost());
            ticket.setEvent(event);
            ticket.setStatus(statusRepository.findByNameIgnoreCase("free"));
            ticket.setNumber(number + i);

            tickets.add(ticket);
        }
        ticketRepository.saveAll(tickets);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findAllFreeTicketsByEventId(Long eventId) {
        return ticketRepository.findTicketsByStatus_NameAndEvent_Id("FREE", eventId);
    }

    @Override
    @Transactional
    public void sellTicket(Ticket ticket, Customer customer) {
        ticket.setCustomer(customer);
        ticket.setStatus(statusRepository.findByNameIgnoreCase("SOLD"));
        customer.getTickets().add(ticket);
        ticketRepository.save(ticket);
        entityManager.flush();
    }

    @Override
    public void deleteAll() {
        ticketRepository.deleteAll();
    }
}
