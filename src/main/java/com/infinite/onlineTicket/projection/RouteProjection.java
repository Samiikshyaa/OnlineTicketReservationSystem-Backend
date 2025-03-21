package com.infinite.onlineTicket.projection;

/**
 * author: Samikshya Timalsina
 * createdDate: 3/17/2025
 **/
public interface RouteProjection {
    Long getBusId();
    String getBusNumber();
    Integer getCapacity();
    Double getPrice();
    Double getDistance();
}
