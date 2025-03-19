package com.infinite.onlineTicket.controller;

import com.infinite.onlineTicket.dto.BusDto;
import com.infinite.onlineTicket.dto.GlobalApiResponse;
import com.infinite.onlineTicket.model.Payment;
import com.infinite.onlineTicket.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
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
 * createdDate: 3/19/2025
 **/
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalApiResponse> saveOrUpdate(@RequestBody BusDto busDto) {
        try {
            Payment payment = paymentService.makePayment();
            seatService.seatCreate(busDto.getId());
            return new ResponseEntity<>(successResponse("Bus saved successfully", bus), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(failureResponse("Bus update failed", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(failureResponse("An error occurred while saving the bus ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
