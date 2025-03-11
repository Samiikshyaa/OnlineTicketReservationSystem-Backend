package com.infinite.onlineTicket.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "routes")
public class Route implements Serializable {
    @Id
    @SequenceGenerator(name ="routes_seq_generator", sequenceName = "routes_seq_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "routes_seq_generator")
    private Long id;

    private String from;

    private String to;

    @Column(name = "departure_time")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    private Double distance;

}