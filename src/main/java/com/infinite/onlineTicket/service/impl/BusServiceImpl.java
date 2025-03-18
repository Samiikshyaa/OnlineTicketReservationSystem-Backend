package com.infinite.onlineTicket.service.impl;

import com.infinite.onlineTicket.dto.BusDto;
import com.infinite.onlineTicket.model.Bus;
import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.model.Seat;
import com.infinite.onlineTicket.model.enums.SeatStatus;
import com.infinite.onlineTicket.projection.SeatProjection;
import com.infinite.onlineTicket.repository.BusRepository;
import com.infinite.onlineTicket.repository.RouteRepository;
import com.infinite.onlineTicket.repository.SeatRepository;
import com.infinite.onlineTicket.service.BusService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private final SeatRepository seatRepository;
    @Override
    public BusDto saveOrUpdate(BusDto busDto) {
        Bus bus;

        Route route = routeRepository.findById(busDto.getRouteId()).orElseThrow(() -> new EntityNotFoundException("Route with ID " + busDto.getRouteId() + " not found"));

        if (busDto.getId() != null && route!=null) {
            bus = busRepository.findById(busDto.getId()).orElseThrow(() -> new EntityNotFoundException("Bus with ID " + busDto.getId() + " not found"));
        } else {
            bus = new Bus();
        }
        bus.setBusNumber(busDto.getBusNumber());
        bus.setCapacity(busDto.getCapacity());
        bus.setRoutes(List.of(route));
        Long id = busRepository.save(bus).getId();
        busDto.setId(id);
        return busDto;
    }

    @Override
    public List<Bus> getAll() {
        return busRepository.findAll();
    }

    @Override
    public void deleteBus(Long busId) {
        busRepository.deleteById(busId);
    }

    @Override
    public List<SeatProjection> seatList(Long busId) {
        return seatRepository.busSeats(busId);
    }

}
