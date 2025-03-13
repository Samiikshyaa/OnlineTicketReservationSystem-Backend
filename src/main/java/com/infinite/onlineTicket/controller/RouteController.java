package com.infinite.onlineTicket.controller;

import com.infinite.onlineTicket.dto.GlobalApiResponse;
import com.infinite.onlineTicket.dto.RouteDto;
import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController extends BaseController {
    private final RouteService routeService;

    @PostMapping("/save")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalApiResponse> saveOrUpdateRoute(@RequestBody RouteDto routeDto) {
        Route route = routeService.saveOrUpdate(routeDto);
        return new ResponseEntity<>(successResponse("Route saved successfully", route), HttpStatus.OK);
    }
}
