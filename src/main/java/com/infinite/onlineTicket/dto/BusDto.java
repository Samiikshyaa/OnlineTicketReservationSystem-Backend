package com.infinite.onlineTicket.dto;

import com.infinite.onlineTicket.model.Seat;
import lombok.*;

import java.util.List;

/**
 * author: Samikshya Timalsina
 * createdDate: 3/13/2025
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BusDto {
    private Long id;
    private String busNumber;
    private Integer capacity;
    private Long routeId;
}
