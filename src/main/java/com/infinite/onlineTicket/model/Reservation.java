package com.infinite.onlineTicket.model;

import com.infinite.onlineTicket.model.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "reservations")
public class Reservation implements Serializable {
    @Id
    @SequenceGenerator(name = "reservations_seq_generator", sequenceName = "reservations_seq_generator",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservations_seq_generator")
    private Long id;

    @Column(name = "reservation_date")
    private LocalDateTime reservationDate;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "fk_reservation_user_id"))
    private User user;

    @ManyToOne(targetEntity = Bus.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_reservation_bus"))
    private Bus bus;

    @OneToMany(targetEntity = Seat.class, fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Seat> seats;
}
