package com.infinite.onlineTicket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infinite.onlineTicket.model.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "seats")
public class Seat implements Serializable {
    @Id
    @SequenceGenerator(name = "seats_seq_generator", sequenceName = "seats_seq_generator", allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "seats_seq_generator")
    private Long id;

    @Column(name = "seat_number")
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_status", nullable = false)
    private SeatStatus seatStatus;

    @ManyToOne(targetEntity = Bus.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_seat_bus"))
    @JsonIgnore
    private Bus bus;
}
