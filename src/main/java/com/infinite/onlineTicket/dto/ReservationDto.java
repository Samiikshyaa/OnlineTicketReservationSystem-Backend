package com.infinite.onlineTicket.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long busId;
    private List<Long> seatNumbers;
}
