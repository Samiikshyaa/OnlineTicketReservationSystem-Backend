package com.infinite.onlineTicket.controller;

import com.infinite.onlineTicket.dto.GlobalApiResponse;
import com.infinite.onlineTicket.dto.ReservationDto;
import com.infinite.onlineTicket.model.Reservation;
import com.infinite.onlineTicket.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: Samikshya Timalsina
 * createdDate: 3/16/2025
 **/

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationController extends BaseController {

    private final ReservationService reservationService;
    @PostMapping("/reserve")
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<GlobalApiResponse> reserveSeats(@RequestBody ReservationDto request) {
         Reservation reservation=reservationService.reserveSeat(request);
        return new ResponseEntity<>(successResponse("Reservations successful",reservation), HttpStatus.OK);
    }
}
