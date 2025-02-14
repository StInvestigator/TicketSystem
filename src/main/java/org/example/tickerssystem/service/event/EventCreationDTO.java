package org.example.tickerssystem.service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.tickerssystem.entity.Place;
import org.example.tickerssystem.service.ticket.TicketPackDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCreationDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String name;
    private List<TicketPackDTO> ticketPacks;
    private Place place;
}
