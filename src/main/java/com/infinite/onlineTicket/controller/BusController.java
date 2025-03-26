package com.infinite.onlineTicket.controller;

import com.infinite.onlineTicket.dto.BusDto;
import com.infinite.onlineTicket.dto.GlobalApiResponse;
import com.infinite.onlineTicket.model.Bus;
import com.infinite.onlineTicket.projection.SeatProjection;
import com.infinite.onlineTicket.repository.BusRepository;
import com.infinite.onlineTicket.service.BusService;
import com.infinite.onlineTicket.service.SeatService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * author: Samikshya Timalsina
 * createdDate: 3/13/2025
 **/

@CrossOrigin
@RestController
@RequestMapping("/api/bus")
@RequiredArgsConstructor
public class BusController extends BaseController {
    private final BusService busService;
    private final BusRepository busRepository;
    private final SeatService seatService;


    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalApiResponse> saveOrUpdate(@RequestBody BusDto busDto) {
        try {
            BusDto bus = busService.saveOrUpdate(busDto);
            seatService.seatCreate(busDto.getId());
            return new ResponseEntity<>(successResponse("Bus saved successfully", bus), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(failureResponse("Bus update failed", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(failureResponse("An error occurred while saving the bus ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN', 'PASSENGER')")
    public ResponseEntity<GlobalApiResponse> getAll() {
        try {
            List<Bus> buses = busService.getAll();
            return new ResponseEntity<>(successResponse("Bus retrieved successfully", buses), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(failureResponse("Bus retrive failed", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(failureResponse("An error occurred while fetching the bus ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{bus-id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalApiResponse> delete(@PathVariable("bus-id") Long id) {
        busService.deleteBus(id);
        Optional<Bus> flag = busRepository.findById(id);
        if (flag.isEmpty()) {
            return new ResponseEntity<>(successResponse("Bus deleted successfully", id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Bus still exists", id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/seats/{busId}")
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<GlobalApiResponse> getBusSeats(@PathVariable("busId") Long id) {
        try {
            List<SeatProjection> seats = busService.seatList(id);
            return new ResponseEntity<>(successResponse("Seats retrived successfully", seats), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(failureResponse("Seats retrive failed", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(failureResponse("An error occurred while retriving the seats ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
