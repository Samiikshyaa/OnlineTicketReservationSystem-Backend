package com.infinite.onlineTicket.repository;

import com.infinite.onlineTicket.model.Seat;
import com.infinite.onlineTicket.projection.SeatProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query(value = "select s.id as id, " +
            "s.seat_number as seatNumber," +
            " s.seat_status as seatStatus " +
            "from seats s where bus_id = :busId", nativeQuery = true )
    List<SeatProjection> busSeats(@Param("busId") Long busId);
}
