package com.infinite.onlineTicket.dto;

import com.infinite.onlineTicket.model.enums.PaymentMethod;
import com.infinite.onlineTicket.model.enums.SeatStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PaymentResponseDto{
    private Long id;
private Double totalAmount;
private String transactionId;
private PaymentMethod paymentMethod;
private LocalDateTime paymentDateAndTime;
private Long reservationId;
private List<String> seatNumbers;

}
