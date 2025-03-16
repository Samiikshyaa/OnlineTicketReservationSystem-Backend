package com.infinite.onlineTicket.repository;

import com.infinite.onlineTicket.model.Bus;
import com.infinite.onlineTicket.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findBySourceAndDestination(String source, String destination);

    @Query(value = "select b.bus_number, b.capacity from buses b join bus_route on b.id = bus_route.bus_id where route_id = ?1",
            nativeQuery = true)
    Optional<Bus> findBusByRouteId(Long routeId);

}
