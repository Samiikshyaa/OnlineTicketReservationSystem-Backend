package com.infinite.onlineTicket.controller;

import com.infinite.onlineTicket.dto.GlobalApiResponse;
import com.infinite.onlineTicket.dto.PaymentDto;
import com.infinite.onlineTicket.dto.PaymentResponseDto;
import com.infinite.onlineTicket.model.Payment;
import com.infinite.onlineTicket.projection.TicketProjection;
import com.infinite.onlineTicket.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * author: Samikshya Timalsina
 * createdDate: 3/19/2025
 **/
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController extends BaseController {
    private final PaymentService paymentService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<GlobalApiResponse> save(@RequestBody PaymentDto paymentDto) {
        try {
            PaymentResponseDto payment = paymentService.makePayment(paymentDto);
            return new ResponseEntity<>(successResponse("Payment successful", payment), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(failureResponse("Bus update failed", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(failureResponse("An error occurred while saving the bus ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/ticket/{id}")
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<GlobalApiResponse> getTicket(@PathVariable("id") Long reservationId) {
        try {
            TicketProjection ticketProjection = paymentService.getTotalAmounts(reservationId);
            return new ResponseEntity<>(successResponse("Ticket fetch successfully", ticketProjection), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(failureResponse("An exception occured", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
