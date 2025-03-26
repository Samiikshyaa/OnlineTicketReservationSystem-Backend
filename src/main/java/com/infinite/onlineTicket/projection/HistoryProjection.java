package com.infinite.onlineTicket.projection;

import com.infinite.onlineTicket.model.enums.PaymentMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface HistoryProjection {
    Double getTotalAmount();

    PaymentMethod getPaymentMethod();

    LocalDateTime getPaymentDate();

    String getFromLocation();

    String getToLocation();

    LocalDate getDepartureDate();

    LocalTime getDepartureTime();

    String getBusNumber();
}
