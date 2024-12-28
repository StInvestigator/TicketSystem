package org.example.tickerssystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String address;

    @Column
    private String name;
}
