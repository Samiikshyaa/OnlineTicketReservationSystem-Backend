package com.infinite.onlineTicket.dto;

import com.infinite.onlineTicket.model.enums.PaymentMethod;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TicketDto {
    private Long paymentId;
    private Double totalAmount;
    private String transactionId;
    private PaymentMethod paymentMethod;
    private LocalDateTime paymentDateAndTime;
    private Long reservationId;
    private List<String> seatNumbers;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private String from;
    private String to;
    private Long userId;
    private String userName;
}
