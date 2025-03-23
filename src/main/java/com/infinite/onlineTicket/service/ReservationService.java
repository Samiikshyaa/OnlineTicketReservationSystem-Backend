package com.infinite.onlineTicket.service;

import com.infinite.onlineTicket.dto.ReservationDto;
import com.infinite.onlineTicket.model.Reservation;
import com.infinite.onlineTicket.projection.dashboard.ReservationByRoute;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReservationService {
    Reservation reserveSeat(ReservationDto reservationDTO);
    List<ReservationByRoute> getReservationByRoute();



}
