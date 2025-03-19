package com.infinite.onlineTicket.service;

import com.infinite.onlineTicket.dto.PaymentDto;
import com.infinite.onlineTicket.dto.PaymentResponseDto;
import com.infinite.onlineTicket.model.Payment;
import com.infinite.onlineTicket.projection.TicketProjection;

public interface PaymentService {
    PaymentResponseDto makePayment(PaymentDto paymentDTO);
    TicketProjection getTotalAmounts(Long reservationId);
//    List<Payment> getAllPayments();
}
