package com.infinite.onlineTicket.service;

import com.infinite.onlineTicket.dto.ReservationDto;
import com.infinite.onlineTicket.model.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation reserveSeat(ReservationDto reservationDTO);
//    List<Reservation> getAllReservations();
}
