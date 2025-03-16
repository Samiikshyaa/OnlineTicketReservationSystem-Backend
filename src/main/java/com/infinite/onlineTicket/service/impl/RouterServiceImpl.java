package com.infinite.onlineTicket.service.impl;

import com.infinite.onlineTicket.dto.RouteDto;
import com.infinite.onlineTicket.model.Bus;
import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.projection.RouteProjection;
import com.infinite.onlineTicket.repository.RouteRepository;
import com.infinite.onlineTicket.service.RouteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouterServiceImpl implements RouteService {
    private final RouteRepository routeRepository;

    @Override
    public Route saveOrUpdate(RouteDto routeDto) {
        Optional<Route> optionalRoute = routeRepository.findBySourceAndDestination(routeDto.getSource(), routeDto.getDestination());

        Route route;

        if (routeDto.getId() != null) {
            route = routeRepository.findById(routeDto.getId()).orElseThrow(() -> new EntityNotFoundException("Route with ID " + routeDto.getId() + " not found"));
        } else {
            route = new Route(); // Create new entity if ID is null (new route)
        }

        // Update fields
        route.setSource(routeDto.getSource());
        route.setDestination(routeDto.getDestination());
        route.setDepartureDate(LocalDate.parse(routeDto.getDepartureDate()));
        route.setDepartureTime(LocalTime.parse(routeDto.getDepartureTime()));
        route.setDistance(routeDto.getDistance());

        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public void deleteRoute(Long routeId) {
        routeRepository.deleteById(routeId);
    }

    @Override
    public Bus getBusDetails(Long routeId) {
         Bus bus =  routeRepository.findBusByRouteId(routeId).orElseThrow(() -> new EntityNotFoundException("Bus in route with ID " + routeId + " not found"));
         return bus;
    }

    @Override
    public List<String> getSource() {
        return routeRepository.findSources();
    }

    @Override
    public List<String> getDestination(String sourceName) {
        return routeRepository.findDestinationsBySource( sourceName);
    }

    @Override
    public List<RouteProjection> getRouteProjections(String source, String destination) {
        return routeRepository.findDetails(source, destination);
    }

}


