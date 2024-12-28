package org.example.tickerssystem.entity;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString(exclude = {"event","customer"})
@EqualsAndHashCode(exclude = {"event","customer"})
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private BigDecimal cost;

    @Column
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable=false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable=false)
    private TicketStatus status;
}
