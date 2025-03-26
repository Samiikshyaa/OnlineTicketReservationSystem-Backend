package com.infinite.onlineTicket.controller;

import com.infinite.onlineTicket.dto.GlobalApiResponse;
import com.infinite.onlineTicket.dto.PaymentDto;
import com.infinite.onlineTicket.dto.PaymentResponseDto;
import com.infinite.onlineTicket.dto.TicketDto;
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
@CrossOrigin
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
            return new ResponseEntity<>(successResponse("Payment saving successful", payment), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(failureResponse("Payment saving failed", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(failureResponse("An error occurred while saving the payment ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/ticket/{id}")
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<GlobalApiResponse> getTicket(@PathVariable("id") Long reservationId) {
        try {
            TicketProjection ticketProjection = paymentService.getTotalAmounts(reservationId);
            return new ResponseEntity<>(successResponse("Ticket information fetched successfully", ticketProjection), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(failureResponse("An exception occured", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/final-ticket/{id}")
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<GlobalApiResponse> fetchTicket(@PathVariable("id") Long ticketId) {
        try {
            TicketDto payment = paymentService.getTicket(ticketId);
            return new ResponseEntity<>(successResponse("Ticket fetch successful", payment), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(failureResponse("Ticket fetch failed", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(failureResponse("An error occurred while fetching the ticket ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
