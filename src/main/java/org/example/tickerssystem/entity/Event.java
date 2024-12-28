package org.example.tickerssystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "tickets")
@EqualsAndHashCode(exclude = "tickets")
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "event_date")
    private Date date;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable=false)
    private Place place;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<Ticket> tickets;
}
