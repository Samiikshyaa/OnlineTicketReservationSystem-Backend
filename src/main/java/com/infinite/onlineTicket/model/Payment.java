package com.infinite.onlineTicket.model;

import com.infinite.onlineTicket.model.enums.PaymentMethod;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment implements Serializable {
    @Id
    @SequenceGenerator(name = "payment_seq_generator", sequenceName = "payment_seq_generator",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq_generator")
    private Long id;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @OneToOne(targetEntity = Reservation.class, fetch = FetchType.LAZY)
    private Reservation reservation;
}
