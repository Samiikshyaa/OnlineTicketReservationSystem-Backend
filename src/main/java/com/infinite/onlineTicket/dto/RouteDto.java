package com.infinite.onlineTicket.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RouteDto {
    private Long id;
    private String source;
    private String destination;
    private String departureDate;
    private String departureTime;
    private Double distance;
    private Double price;
}
