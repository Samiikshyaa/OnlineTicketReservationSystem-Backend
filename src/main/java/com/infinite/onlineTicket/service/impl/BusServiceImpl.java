package com.infinite.onlineTicket.service.impl;

import com.infinite.onlineTicket.dto.BusDto;
import com.infinite.onlineTicket.model.Bus;
import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.repository.BusRepository;
import com.infinite.onlineTicket.repository.RouteRepository;
import com.infinite.onlineTicket.service.BusService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * author: Samikshya Timalsina
 * createdDate: 3/13/2025
 **/

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    @Override
    public BusDto saveOrUpdate(BusDto busDto) {
        Bus bus;
        Route route;
        Optional<Route> optionalRoute = routeRepository.findById(busDto.getRouteId());
        if (busDto.getId() != null && optionalRoute.isPresent()) {
            bus = busRepository.findById(busDto.getId()).orElseThrow(() -> new EntityNotFoundException("Bus with ID " + busDto.getId() + " not found"));
            route = routeRepository.findById(busDto.getRouteId()).orElseThrow(() -> new EntityNotFoundException("Route with ID " + busDto.getRouteId() + " not found"));
        } else {
            bus = new Bus(); // Create new entity if ID is null (new route)
        }

        bus.setBusNumber(busDto.getBusNumber());
        bus.setCapacity(busDto.getCapacity());
        bus.setRoutes(List.of(optionalRoute.get()));
        Long id = busRepository.save(bus).getId();
        busDto.setId(id);
        return busDto;
    }

    @Override
    public List<BusDto> getAll() {
        return List.of();
    }

    @Override
    public void deleteBus(Long routeId) {

    }
}
