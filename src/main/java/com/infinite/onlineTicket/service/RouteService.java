package com.infinite.onlineTicket.service;

import com.infinite.onlineTicket.dto.RouteDto;
import com.infinite.onlineTicket.model.Route;

public interface RouteService {
    Route saveOrUpdate(RouteDto routeDto);
}
