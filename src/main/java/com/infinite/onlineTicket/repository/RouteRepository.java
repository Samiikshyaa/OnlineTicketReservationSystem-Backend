package com.infinite.onlineTicket.repository;

import com.infinite.onlineTicket.model.Bus;
import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.projection.RouteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Source;
import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findBySourceAndDestination(String source, String destination);

    @Query(value = "select b.bus_number, b.capacity from buses b join bus_route on b.id = bus_route.bus_id where route_id = ?1",
            nativeQuery = true)
    Optional<Bus> findBusByRouteId(Long routeId);

    @Query(value = "select source from routes", nativeQuery = true)
    List<String> findSources();

    @Query(value = "select destination from routes where source = ?1", nativeQuery = true)
    List<String> findDestinationsBySource(String source);

    @Query(value = "SELECT b.bus_number AS busNumber, " +
            "       b.capacity AS capacity, " +
            "       r.price AS price, " +
            "       r.distance AS distance " +
            "FROM buses b " +
            "JOIN bus_route br ON b.id = br.bus_id " +
            "JOIN routes r ON r.id = br.route_id " +
            "WHERE r.source = ?1 AND r.destination = ?2",
            nativeQuery = true)
    List<RouteProjection> findDetails(String source, String destination);




}
