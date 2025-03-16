package com.infinite.onlineTicket.service;

import com.infinite.onlineTicket.dto.PaymentDto;
import com.infinite.onlineTicket.model.Payment;

public interface PaymentService {
    Payment makePayment(PaymentDto paymentDTO);
//    List<Payment> getAllPayments();
}
