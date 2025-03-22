package com.infinite.onlineTicket.controller;

import com.infinite.onlineTicket.dto.GlobalApiResponse;
import com.infinite.onlineTicket.projection.dashboard.RouteBusProjection;
import com.infinite.onlineTicket.service.ReservationService;
import com.infinite.onlineTicket.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * author: Samikshya Timalsina
 * createdDate: 3/16/2025
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController extends BaseController {
    private final RouteService routeService;
    private final ReservationService reservationService;


    @GetMapping("/busCountByRoute")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalApiResponse> getBusCountByRoute() {
        try{
        List<RouteBusProjection> routeBusDto = routeService.getRouteAndBusCount();
        return new ResponseEntity<>(successResponse("Data fetch successful", routeBusDto), HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(failureResponse("Fetch Failed", e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/daily")
    public ResponseEntity<GlobalApiResponse> getDailyReservations() {
        Map<String, Object> stats = reservationService.getDailyReservations();
        return new ResponseEntity<>(successResponse("Daily data retrived successfully", stats), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/weekly")
    public ResponseEntity<GlobalApiResponse> getWeeklyReservations() {
        Map<String, Object> stats = reservationService.getWeeklyReservations();
        return new ResponseEntity<>(successResponse("Weekly data retrived successfully", stats), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/monthly")
    public ResponseEntity<GlobalApiResponse> getMonthlyReservations() {
        Map<String, Object> stats = reservationService.getMonthlyReservations();
        return new ResponseEntity<>(successResponse("Monthly data retrived successfully", stats), HttpStatus.OK);
    }

    @GetMapping("/yearly")
    public ResponseEntity<GlobalApiResponse> getYearlyReservations() {
        Map<String, Object> stats = reservationService.getYearlyReservations();
        return new ResponseEntity<>(successResponse("Yearly data retrived successfully", stats), HttpStatus.OK);
    }
}
