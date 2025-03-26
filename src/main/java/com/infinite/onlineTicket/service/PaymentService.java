package com.infinite.onlineTicket.service;

import com.infinite.onlineTicket.dto.PaymentDto;
import com.infinite.onlineTicket.dto.PaymentResponseDto;
import com.infinite.onlineTicket.dto.TicketDto;
import com.infinite.onlineTicket.projection.HistoryProjection;
import com.infinite.onlineTicket.projection.TicketProjection;

import java.util.List;

public interface PaymentService {
    PaymentResponseDto makePayment(PaymentDto paymentDTO);

    TicketProjection getTotalAmounts(Long reservationId);

    TicketDto getTicket(Long paymentId);

    List<HistoryProjection> getHistory(String userName);
}
