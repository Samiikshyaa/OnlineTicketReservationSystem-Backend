package com.infinite.onlineTicket.repository;

import com.infinite.onlineTicket.model.Bus;
import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.projection.RouteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "SELECT b.id AS busId, " +
            " b.bus_number AS busNumber,\n" +
            " b.capacity AS capacity,\n" +
            " r.distance as distance,\n" +
            " r.price as price,\n" +
            " r.\"source\",\n" +
            " r.destination\n" +
            " FROM buses b \n" +
            " JOIN bus_route br ON b.id = br.bus_id\n" +
            " join routes r on r.id = br.route_id where r.\"source\" = :source\n" +
            " and r.destination = :destination\n",
            nativeQuery = true)
    List<RouteProjection> findDetails(@Param("source") String source, @Param("destination") String destination);




}
