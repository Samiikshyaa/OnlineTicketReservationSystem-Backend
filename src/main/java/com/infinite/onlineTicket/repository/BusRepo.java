package com.infinite.onlineTicket.repository;

import com.infinite.onlineTicket.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepo extends JpaRepository<Bus, Long> {
}
