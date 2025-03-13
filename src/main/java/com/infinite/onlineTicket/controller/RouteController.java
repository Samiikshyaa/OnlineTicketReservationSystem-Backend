package com.infinite.onlineTicket.controller;

import com.infinite.onlineTicket.dto.GlobalApiResponse;
import com.infinite.onlineTicket.dto.RouteDto;
import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.repository.RouteRepository;
import com.infinite.onlineTicket.service.RouteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController extends BaseController {
    private final RouteService routeService;
    private final RouteRepository routeRepository;

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


    @DeleteMapping("/delete/{route-id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalApiResponse> deleteRoute(@PathVariable("route-id") Long id){
        routeService.deleteRoute(id);
        Optional<Route> flag = routeRepository.findById(id);
        if (flag.isEmpty()){
            return new ResponseEntity<>(successResponse("Route deleted successfully",id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Route still exists",id),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
