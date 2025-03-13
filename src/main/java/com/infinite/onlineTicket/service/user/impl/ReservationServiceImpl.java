package com.infinite.onlineTicket.service.user.impl;

import com.infinite.onlineTicket.dto.ReservationDto;
import com.infinite.onlineTicket.model.Bus;
import com.infinite.onlineTicket.model.Reservation;
import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.model.User;
import com.infinite.onlineTicket.model.enums.SeatStatus;
import com.infinite.onlineTicket.repository.BusRepository;
import com.infinite.onlineTicket.repository.ReservationRepository;
import com.infinite.onlineTicket.repository.RouteRepository;
import com.infinite.onlineTicket.repository.UserRepository;
import com.infinite.onlineTicket.service.user.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
//    private final ReservationRepository reservationRepository;
//    private final UserRepository userRepository;
//    private final BusRepository busRepository;
//    private final RouteRepository routeRepository;
//
//    @Override
//    public Reservation createReservation(ReservationDto reservationDTO) {
//        User user = userRepository.findById(reservationDTO.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Bus bus = busRepository.findById(reservationDTO.getBusId())
//                .orElseThrow(() -> new RuntimeException("Bus not found"));
//
//        Route route = (Route) routeRepository.findById(reservationDTO.getRouteId())
//                .orElseThrow(() -> new RuntimeException("Route not found"));
//
//        Reservation reservation = new Reservation();
//        reservation.setUser(user);
//        reservation.setBus(bus);
//        reservation.setRoute(route);
//        reservation.setSeatNumber(reservationDTO.getSeatNumber());
//        reservation.setReservationDate(LocalDateTime.now());
//        reservation.setSeatStatus(SeatStatus.RESERVED);
//
//        return reservationRepository.save(reservation);
//    }
//
//    @Override
//    public List<Reservation> getAllReservations() {
//        return reservationRepository.findAll();
//    }
}
