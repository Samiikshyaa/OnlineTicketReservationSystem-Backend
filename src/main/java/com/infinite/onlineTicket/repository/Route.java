package com.infinite.onlineTicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Route extends JpaRepository<Route, Long> {
}
