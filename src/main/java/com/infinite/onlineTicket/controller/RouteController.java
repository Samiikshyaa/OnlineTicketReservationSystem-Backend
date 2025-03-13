package com.infinite.onlineTicket.controller;

import com.infinite.onlineTicket.dto.GlobalApiResponse;
import com.infinite.onlineTicket.dto.RouteDto;
import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.service.RouteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController extends BaseController {
    private final RouteService routeService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalApiResponse> saveOrUpdateRoute(@RequestBody RouteDto routeDto) {
        try {
            Route route = routeService.saveOrUpdate(routeDto);
            return new ResponseEntity<>(successResponse("Route saved successfully", route), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(failureResponse("Route update failed" ,e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(failureResponse("An error occurred while saving the route ",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN', 'PASSENGER')")
    public ResponseEntity<GlobalApiResponse> getAllRoutes() {
        try {
            List<Route> routes = routeService.getAllRoutes();
            return new ResponseEntity<>(successResponse("Routes retrieved successfully", routes), HttpStatus.OK);
        }catch (EntityNotFoundException e) {
            return new ResponseEntity<>(failureResponse("Route retrive failed" ,e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(failureResponse("An error occurred while fetching the route ",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
