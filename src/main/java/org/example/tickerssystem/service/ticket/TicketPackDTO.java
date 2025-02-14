package org.example.tickerssystem.service.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketPackDTO {
    private BigDecimal cost;
    private Integer count;
}
