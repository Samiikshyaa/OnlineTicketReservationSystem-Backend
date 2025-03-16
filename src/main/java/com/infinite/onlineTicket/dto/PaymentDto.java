package com.infinite.onlineTicket.dto;

import com.infinite.onlineTicket.model.enums.PaymentMethod;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Double totalAmount;
    private String transactionId;
    private PaymentMethod paymentMethod;
    private Long reservationId;
}
