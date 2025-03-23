package com.infinite.onlineTicket.service.impl;

import com.infinite.onlineTicket.dto.ReservationDto;
import com.infinite.onlineTicket.model.Bus;
import com.infinite.onlineTicket.model.Reservation;
import com.infinite.onlineTicket.model.Seat;
import com.infinite.onlineTicket.model.User;
import com.infinite.onlineTicket.model.enums.SeatStatus;
import com.infinite.onlineTicket.projection.dashboard.ReservationByRoute;
import com.infinite.onlineTicket.repository.*;
import com.infinite.onlineTicket.service.ReservationService;
import com.infinite.onlineTicket.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final SeatRepository seatRepository;

    @Transactional
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
    public List<ReservationByRoute> getReservationByRoute() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekAgo = today.minusDays(7); // Get the date 7 days ago

        return reservationRepository.getReservationByRoute(oneWeekAgo.atStartOfDay(), today.atTime(23, 59, 59));
    }

}
