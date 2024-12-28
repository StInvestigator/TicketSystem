package org.example.tickerssystem.service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.tickerssystem.service.place.PlaceDTO;
import org.example.tickerssystem.service.ticket.TicketPackDTO;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCreationDTO {
    private Date date;
    private String name;
    private List<TicketPackDTO> ticketPacks;
    private PlaceDTO place;
}
