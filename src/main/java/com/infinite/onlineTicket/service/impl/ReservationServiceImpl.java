package com.infinite.onlineTicket.service.impl;

import com.infinite.onlineTicket.dto.ReservationDto;
import com.infinite.onlineTicket.model.*;
import com.infinite.onlineTicket.model.enums.SeatStatus;
import com.infinite.onlineTicket.repository.*;
import com.infinite.onlineTicket.service.ReservationService;
import com.infinite.onlineTicket.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final SeatRepository seatRepository;

    @Override
    public Reservation reserveSeat(ReservationDto reservationDTO) {
        String username = SecurityUtils.getLoggedInUsername();
        if (username == null) {
            throw new RuntimeException("User not authenticated");
        }

        User user = userRepository.findByUserName(username);

        Bus bus = busRepository.findById(reservationDTO.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        List<Seat> availableSeats = seatRepository.findAllById(reservationDTO.getSeatNumbers()).stream()
                .filter(seat -> seat.getSeatStatus() == SeatStatus.AVAILABLE)
                .collect(Collectors.toList());

        if (availableSeats.isEmpty()) {
            throw new RuntimeException("No available seats found for reservation");
        }

        availableSeats.forEach(seat -> seat.setSeatStatus(SeatStatus.RESERVED));

        Reservation reservation = Reservation.builder()
                .reservationDate(LocalDateTime.now())
                .user(user)
                .bus(bus)
                .seats(availableSeats)
                .build();

        seatRepository.saveAll(availableSeats);
        return reservationRepository.save(reservation);
    }

    @Override
    public Map<String, Object> getDailyReservations() {
        LocalDate today = LocalDate.now();
        long count = reservationRepository.countByReservationDateBetween(today.atStartOfDay(), today.plusDays(1).atStartOfDay());
        return createStatsResponse("Daily", count);
    }

    @Override
    public Map<String, Object> getWeeklyReservations() {
        LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate endOfWeek = startOfWeek.plusDays(7);
        long count = reservationRepository.countByReservationDateBetween(startOfWeek.atStartOfDay(), endOfWeek.atStartOfDay());
        return createStatsResponse("Weekly", count);
    }

    @Override
    public Map<String, Object> getMonthlyReservations() {
        LocalDate startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = startOfMonth.plusMonths(1);
        long count = reservationRepository.countByReservationDateBetween(startOfMonth.atStartOfDay(), endOfMonth.atStartOfDay());
        return createStatsResponse("Monthly", count);
    }

    @Override
    public Map<String, Object> getYearlyReservations() {
        LocalDate startOfYear = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
        LocalDate endOfYear = startOfYear.plusYears(1);
        long count = reservationRepository.countByReservationDateBetween(startOfYear.atStartOfDay(), endOfYear.atStartOfDay());
        return createStatsResponse("Yearly", count);
    }


    private Map<String, Object> createStatsResponse(String period, long count) {
        Map<String, Object> response = new HashMap<>();
        response.put("period", period);
        response.put("reservations", count);
        response.put("date", LocalDate.now());
        return response;
    }
//    @Override
//    public List<Reservation> getAllReservations() {
//        return reservationRepository.findAll();
//    }
}
