package com.infinite.onlineTicket.repository;

import com.infinite.onlineTicket.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Long countByReservationDateBetween(LocalDateTime start, LocalDateTime end);
}
