package com.infinite.onlineTicket.service;

import com.infinite.onlineTicket.dto.RouteDto;
import com.infinite.onlineTicket.model.Route;

import java.util.List;

public interface RouteService {
    Route saveOrUpdate(RouteDto routeDto);
    List<Route> getAllRoutes();
    void deleteRoute(Long routeId);
}
