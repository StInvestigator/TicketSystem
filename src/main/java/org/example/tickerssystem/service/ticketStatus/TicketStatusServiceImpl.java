package org.example.tickerssystem.service.ticketStatus;

import org.example.tickerssystem.entity.TicketStatus;
import org.example.tickerssystem.repository.ticketStatus.TicketStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TicketStatusServiceImpl implements TicketStatusService {
    @Autowired
    private TicketStatusRepository ticketStatusRepository;

    @Override
    public TicketStatus getTicketStatus(String name) {
        return ticketStatusRepository.findByNameIgnoreCase(name);
    }
}
