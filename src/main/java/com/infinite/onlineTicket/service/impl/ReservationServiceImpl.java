package com.infinite.onlineTicket.service.impl;

import com.infinite.onlineTicket.dto.ReservationDto;
import com.infinite.onlineTicket.model.*;
import com.infinite.onlineTicket.model.enums.SeatStatus;
import com.infinite.onlineTicket.repository.*;
import com.infinite.onlineTicket.service.ReservationService;
import com.infinite.onlineTicket.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

//    @Override
//    public List<Reservation> getAllReservations() {
//        return reservationRepository.findAll();
//    }
}
