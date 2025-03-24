package com.infinite.onlineTicket.service.impl;

import com.infinite.onlineTicket.dto.PaymentDto;
import com.infinite.onlineTicket.dto.PaymentResponseDto;
import com.infinite.onlineTicket.dto.TicketDto;
import com.infinite.onlineTicket.model.Payment;
import com.infinite.onlineTicket.model.Reservation;
import com.infinite.onlineTicket.model.enums.PaymentMethod;
import com.infinite.onlineTicket.projection.HistoryProjection;
import com.infinite.onlineTicket.projection.TicketProjection;
import com.infinite.onlineTicket.repository.PaymentRepository;
import com.infinite.onlineTicket.repository.ReservationRepository;
import com.infinite.onlineTicket.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
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
                    .paymentId(payment.getId())
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

    @Override
    public TicketDto getTicket(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));
        Reservation reservation = payment.getReservation();
        List<String> seatNumbers = reservation.getSeats().stream().map(x-> x.getSeatNumber()).toList();
        TicketProjection ticketInformation = paymentRepository.getTicketDetails(reservation.getId());
        TicketDto ticketDto = TicketDto.builder()
                .paymentId(payment.getId())
                .totalAmount(payment.getTotalAmount())
                .transactionId(payment.getTransactionId())
                .paymentMethod(payment.getPaymentMethod())
                .paymentDateAndTime(payment.getPaymentDate())
                .reservationId(reservation.getId())
                .seatNumbers(seatNumbers)
                .departureDate(ticketInformation.getDepartureDate())
                .departureTime(ticketInformation.getDepartureTime())
                .from(ticketInformation.getSource())
                .to(ticketInformation.getDestination())
                .userId(ticketInformation.getUserId())
                .userName(ticketInformation.getUserName())
                .build();
        return ticketDto;
    }

    @Override
    public List<HistoryProjection> getHistory(String userName) {
        List<HistoryProjection> historyDtos = paymentRepository.getPaymentHistory(userName);

        return historyDtos;
    }
}
