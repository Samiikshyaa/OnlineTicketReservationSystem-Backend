package com.infinite.onlineTicket.controller;

import com.infinite.onlineTicket.dto.GlobalApiResponse;
import com.infinite.onlineTicket.projection.HistoryProjection;
import com.infinite.onlineTicket.service.PaymentService;
import com.infinite.onlineTicket.utils.SecurityUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController extends BaseController {
    private final PaymentService paymentService;

    @GetMapping
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<GlobalApiResponse> fetchHistory() {
        try {
            String username = SecurityUtils.getLoggedInUsername();
            if (username == null) {
                throw new RuntimeException("User not authenticated");
            }
            List<HistoryProjection> history = paymentService.getHistory(username);
            return new ResponseEntity<>(successResponse("History fetch successful", history), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(failureResponse("History fetch failed", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(failureResponse("An error occurred while fetching history ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
