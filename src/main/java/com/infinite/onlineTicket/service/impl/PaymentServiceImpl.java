package com.infinite.onlineTicket.service.impl;

import com.infinite.onlineTicket.dto.PaymentDto;
import com.infinite.onlineTicket.model.Payment;
import com.infinite.onlineTicket.model.Reservation;
import com.infinite.onlineTicket.repository.PaymentRepository;
import com.infinite.onlineTicket.repository.ReservationRepository;
import com.infinite.onlineTicket.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public Payment makePayment(PaymentDto paymentDTO) {
        Reservation reservation = reservationRepository.findById(paymentDTO.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // Create and Save Payment
        Payment payment = Payment.builder()
                .totalAmount(paymentDTO.getTotalAmount())
                .transactionId(paymentDTO.getTransactionId())
                .paymentMethod(paymentDTO.getPaymentMethod())
                .paymentDate(LocalDateTime.now())
                .reservation(reservation)
                .build();

        return paymentRepository.save(payment);
    }
//
//    @Override
//    public List<Payment> getAllPayments() {
//        return List.of();
//    }
}
