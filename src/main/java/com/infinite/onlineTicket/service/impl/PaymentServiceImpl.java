package com.infinite.onlineTicket.service.impl;

import com.infinite.onlineTicket.dto.PaymentDto;
import com.infinite.onlineTicket.dto.PaymentResponseDto;
import com.infinite.onlineTicket.model.Payment;
import com.infinite.onlineTicket.model.Reservation;
import com.infinite.onlineTicket.model.enums.PaymentMethod;
import com.infinite.onlineTicket.projection.TicketProjection;
import com.infinite.onlineTicket.repository.PaymentRepository;
import com.infinite.onlineTicket.repository.ReservationRepository;
import com.infinite.onlineTicket.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public PaymentResponseDto makePayment(PaymentDto paymentDTO) {
            Reservation reservation = reservationRepository.findById(paymentDTO.getReservationId())
                    .orElseThrow(() -> new RuntimeException("Reservation not found"));

            TicketProjection ticket = paymentRepository.getTicketDetails(paymentDTO.getReservationId());
            Double totalAmount = ticket.getTotalAmount();

            // Create and Save Payment
            Payment payment = Payment.builder()
                    .totalAmount(totalAmount)
                    .transactionId(paymentDTO.getTransactionId())
                    .paymentMethod((paymentDTO.getPaymentMethod().equalsIgnoreCase("khalti")) ? PaymentMethod.KHALTI : PaymentMethod.ESEWA)
                    .paymentDate(LocalDateTime.now())
                    .reservation(reservation)
                    .build();
            Payment pay = paymentRepository.save(payment);

            List<String> seatNumbers = reservation.getSeats().stream().map(x-> x.getSeatNumber()).toList();
            PaymentResponseDto paymentResponseDto = PaymentResponseDto.builder()
                    .id(payment.getId())
                    .totalAmount(totalAmount)
                    .transactionId(paymentDTO.getTransactionId())
                    .paymentMethod(payment.getPaymentMethod())
                    .paymentDateAndTime(payment.getPaymentDate())
                    .reservationId(reservation.getId())
                    .seatNumbers(seatNumbers)
                    .build();
            return paymentResponseDto;
    }

    @Override
    public TicketProjection getTotalAmounts(Long reservationId) {
        return paymentRepository.getTicketDetails(reservationId);
    }
//
//    @Override
//    public List<Payment> getAllPayments() {
//        return List.of();
//    }
}
