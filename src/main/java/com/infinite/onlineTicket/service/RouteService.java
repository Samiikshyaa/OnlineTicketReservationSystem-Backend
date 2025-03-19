package com.infinite.onlineTicket.service;

import com.infinite.onlineTicket.dto.RouteDto;
import com.infinite.onlineTicket.model.Bus;
import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.projection.RouteProjection;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RouteService {
    Route saveOrUpdate(RouteDto routeDto);
    List<Route> getAllRoutes();
    void deleteRoute(Long routeId);
    Bus getBusDetails(Long routeId);
    List<String> getSource();
    List<String> getDestination(String sourceName);
    List<RouteProjection> getRouteProjections(String sourceName, String destinationName, LocalDate date, LocalTime time);
}
