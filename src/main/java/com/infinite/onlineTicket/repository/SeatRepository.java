package com.infinite.onlineTicket.repository;

import com.infinite.onlineTicket.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
//    @Query(value = "select * from seat", nativeQuery = true )
//    List<Seat> findAll();
}
