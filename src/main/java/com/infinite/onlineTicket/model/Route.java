package com.infinite.onlineTicket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
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
    @SequenceGenerator(name = "routes_seq_generator", sequenceName = "routes_seq_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "routes_seq_generator")
    private Long id;

    private String source;

    private String destination;

    @Column(name = "departure_date", nullable = false, columnDefinition = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @Column(name = "departure_time", nullable = false, columnDefinition = "TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime departureTime;

    private Double price;

    private Double distance;
}