package com.infinite.onlineTicket.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface TicketProjection {
    Long getReserveId();
    LocalDateTime getReserveDate();
    Long getUserId();
    String getUserName();
    String getBusNumber();
    String getSource();
    String getDestination();
    LocalDate getDepartureDate();
    LocalTime getDepartureTime();
    Double getRate();
    Integer getSeatCount();
    Double getTotalAmount();


}
