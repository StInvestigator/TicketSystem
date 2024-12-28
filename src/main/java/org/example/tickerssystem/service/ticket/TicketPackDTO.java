package org.example.tickerssystem.service.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TicketPackDTO {
    private BigDecimal cost;
    private Integer count;
}
