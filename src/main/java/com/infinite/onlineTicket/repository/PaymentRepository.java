package com.infinite.onlineTicket.repository;

import com.infinite.onlineTicket.model.Payment;
import com.infinite.onlineTicket.projection.HistoryProjection;
import com.infinite.onlineTicket.projection.TicketProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
@Query(value = "SELECT \n" +
        "    r.id as reserveId, \n" +
        "    r.reservation_date as reserveDate, \n" +
        "    u.user_id as userId, \n" +
        "    u.user_name as userName, \n" +
        "    b.bus_number as busNumber, \n" +
        "    r2.\"source\" as source, \n" +
        "    r2.destination as destination, \n" +
        "    r2.departure_date as departureDate, \n" +
        "    r2.departure_time as departureTime,\n" +
        "    r2.price as rate,\n" +
        "    COUNT(s.seat_number) AS seatCount,\n" +
        "    r2.price * COUNT(s.seat_number) as totalAmount\n" +
        "FROM \n" +
        "    reservations r \n" +
        "JOIN \n" +
        "    buses b ON r.bus_id = b.id\n" +
        "JOIN \n" +
        "    bus_route br ON br.bus_id = b.id\n" +
        "JOIN \n" +
        "    routes r2 ON br.route_id = r2.id\n" +
        "JOIN \n" +
        "    users u ON r.user_id = u.user_id\n" +
        "JOIN \n" +
        "    reservations_seats rs ON rs.reservation_id = r.id\n" +
        "JOIN \n" +
        "    seats s ON rs.seats_id = s.id \n" +
        "WHERE \n" +
        "    rs.reservation_id = :reservationId\n" +
        "GROUP BY \n" +
        "    r.id, \n" +
        "    r.reservation_date, \n" +
        "    u.user_id, \n" +
        "    u.user_name, \n" +
        "    b.bus_number, \n" +
        "    r2.\"source\", \n" +
        "    r2.destination, \n" +
        "    r2.departure_date, \n" +
        "    r2.departure_time,\n" +
        "    r2.price;", nativeQuery = true)
TicketProjection getTicketDetails(@Param("reservationId") Long reservationId);

@Query(value = "select p.total_amount as totalAmount, " +
        "p.payment_date as paymentDate, " +
        "p.payment_method as paymentMethod,\n" +
        "r2.source as fromLocation, " +
        "r2.destination as toLocation, " +
        "r2.departure_date as departureDate, \n" +
        "r2.departure_time as departureTime, " +
        "b.bus_number as busNumber\n" +
        "from payments p\n" +
        "join reservations r\n" +
        "on p.id = r.id \n" +
        "join users u\n" +
        "on u.user_id = r.user_id \n" +
        "join buses b\n" +
        "on r.bus_id = b.id\n" +
        "join bus_route br\n" +
        "on br.bus_id = b.id\n" +
        "join routes r2\n" +
        "on r2.id = br.route_id\n" +
        "where user_name = :userName", nativeQuery = true)
List<HistoryProjection> getPaymentHistory(@Param("userName")String userName);
}
