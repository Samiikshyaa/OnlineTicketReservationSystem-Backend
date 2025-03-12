package com.infinite.onlineTicket.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long userId;
    private Long busId;
    private Long routeId;
    private String seatNumber;
}
