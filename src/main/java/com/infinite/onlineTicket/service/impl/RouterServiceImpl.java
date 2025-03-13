package com.infinite.onlineTicket.service.impl;

import com.infinite.onlineTicket.dto.RouteDto;
import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.repository.RouteRepository;
import com.infinite.onlineTicket.service.RouteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouterServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    @Override
    public Route saveOrUpdate(RouteDto routeDto) {
        Optional<Route> optionalRoute = routeRepository.findBySourceAndDestination(routeDto.getSource(),
                routeDto.getDestination());

        Long id = routeDto.getId();
        if(id!=null && optionalRoute.isEmpty()) {
            throw new EntityNotFoundException("Route with ID " + routeDto.getId() + " not found");
        }
         return routeRepository.save(Route.builder()
                            .id(id)
                            .source(routeDto.getSource())
                            .destination(routeDto.getDestination())
                            .departureDate(LocalDate.parse(routeDto.getDepartureDate()))
                            .departureTime(LocalTime.parse(routeDto.getDepartureTime()))
                            .distance(routeDto.getDistance())
                    .build());

        }
}


