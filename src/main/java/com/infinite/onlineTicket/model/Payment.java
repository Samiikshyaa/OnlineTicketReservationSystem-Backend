package com.infinite.onlineTicket.model;

import com.infinite.onlineTicket.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "payments")
public class Payment implements Serializable {
    @Id
    @SequenceGenerator(name = "payment_seq_generator", sequenceName = "payment_seq_generator",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq_generator")
    private Long id;
    @Column(name = "total_amount")
    private Double totalAmount;
    @Column(name = "transaction_id", unique = true)
    private String transactionId;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @OneToOne(targetEntity = Reservation.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_payment_reservation_id"))
    private Reservation reservation;

}
