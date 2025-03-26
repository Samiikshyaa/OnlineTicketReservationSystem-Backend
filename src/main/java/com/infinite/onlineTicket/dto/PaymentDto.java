package com.infinite.onlineTicket.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String transactionId;
    private String paymentMethod;
    private Long reservationId;
}
