package com.infinite.onlineTicket.service.impl;

import com.infinite.onlineTicket.dto.SeatDto;
import com.infinite.onlineTicket.model.Bus;
import com.infinite.onlineTicket.model.Seat;
import com.infinite.onlineTicket.model.enums.SeatStatus;
import com.infinite.onlineTicket.repository.BusRepository;
import com.infinite.onlineTicket.repository.SeatRepository;
import com.infinite.onlineTicket.service.SeatService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final BusRepository busRepository;


    @Transactional
    @Override
    public void seatCreate(Long busId) {
        Seat seat;
        Bus bus = busRepository.findById(busId)
                .orElseThrow(()-> new EntityNotFoundException("Bus with Id"+busId+" not found"));
        Integer busCapacity = bus.getCapacity();
        for (int i = 1; i <= busCapacity ; i++) {
            seat = Seat.builder()
                    .seatStatus(SeatStatus.AVAILABLE)
                    .seatNumber("S"+i)
                    .bus(bus)
                    .build();
            seatRepository.save(seat);
        }
    }
}
