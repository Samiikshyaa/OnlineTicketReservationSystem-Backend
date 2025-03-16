package com.infinite.onlineTicket.service;

import com.infinite.onlineTicket.dto.BusDto;
import com.infinite.onlineTicket.dto.RouteDto;
import com.infinite.onlineTicket.model.Bus;
import com.infinite.onlineTicket.model.Route;

import java.util.List;

/**
 * author: Samikshya Timalsina
 * createdDate: 3/13/2025
 **/
public interface BusService {
    BusDto saveOrUpdate(BusDto busDto);
    List<Bus> getAll();
    void deleteBus(Long busId);
}
