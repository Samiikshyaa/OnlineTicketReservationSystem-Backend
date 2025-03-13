package com.infinite.onlineTicket.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "routes")
public class Route implements Serializable {
    @Id
    @SequenceGenerator(name ="routes_seq_generator", sequenceName = "routes_seq_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "routes_seq_generator")
    private Long id;

    private String source;

    private String destination;

    @Column(name = "departure_time")
    private String departureTime;

    private Double distance;
}