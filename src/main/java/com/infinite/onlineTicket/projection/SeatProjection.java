package com.infinite.onlineTicket.projection;

import com.infinite.onlineTicket.model.enums.SeatStatus;

public interface SeatProjection {
    Long getId();

    String getSeatNumber();

    SeatStatus getSeatStatus();
}
