package com.infinite.onlineTicket.service;

import com.infinite.onlineTicket.dto.ReservationDto;
import com.infinite.onlineTicket.model.Reservation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public interface ReservationService {
    Reservation reserveSeat(ReservationDto reservationDTO);

    //    List<Reservation> getAllReservations();
    public Map<String, Object> getDailyReservations();

    public Map<String, Object> getWeeklyReservations();

    public Map<String, Object> getMonthlyReservations();

    public Map<String, Object> getYearlyReservations();

}
