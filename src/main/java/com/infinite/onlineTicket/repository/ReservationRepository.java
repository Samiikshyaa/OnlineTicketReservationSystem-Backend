package com.infinite.onlineTicket.repository;

import com.infinite.onlineTicket.model.Reservation;
import com.infinite.onlineTicket.projection.dashboard.ReservationByRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Long countByReservationDateBetween(LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT COUNT(rs.reservation_id) AS reservationCount, \n" +
            "       (r.source || '->' || r.destination) AS routeName\n" +
            "FROM bus_route br\n" +
            "JOIN buses b ON br.bus_id = b.id\n" +
            "JOIN routes r ON r.id = br.route_id\n" +
            "JOIN reservations r2 ON b.id = r2.bus_id\n" +
            "JOIN reservations_seats rs ON r2.id = rs.reservation_id\n" +
            "WHERE r2.reservation_date BETWEEN :fromDate AND :toDate\n" +
            "GROUP BY r.source, r.destination;", nativeQuery = true)
    List<ReservationByRoute> getReservationByRoute(@Param("fromDate") LocalDateTime start, @Param("toDate") LocalDateTime end);
}
