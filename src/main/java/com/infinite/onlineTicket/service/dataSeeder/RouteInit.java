package com.infinite.onlineTicket.service.dataSeeder;

import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.repository.RouteRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RouteInit  {
//    implements CommandLineRunner -> this is run before hibernate creates table

    private final RouteRepository routeRepository;

    @PostConstruct
    public void seedRoutes() {
        if (routeRepository.count() == 0) {
            List<Route> routes = List.of(
                    new Route(null, "Kathmandu", "Pokhara", "7:00 AM", 200.0),
                    new Route(null, "Kathmandu", "Chitwan", "6:30 AM", 160.0),
                    new Route(null, "Kathmandu", "Biratnagar", "8:00 AM", 380.0),
                    new Route(null, "Pokhara", "Butwal", "9:30 AM", 120.0),
                    new Route(null, "Birgunj", "Kathmandu", "10:00 AM", 280.0),
                    new Route(null, "Dhangadhi", "Nepalgunj", "5:45 AM", 200.0),
                    new Route(null, "Kathmandu", "Dharan", "7:15 AM", 390.0),
                    new Route(null, "Biratnagar", "Janakpur", "6:00 AM", 220.0),
                    new Route(null, "Pokhara", "Lumbini", "7:45 AM", 190.0)
            );
            routeRepository.saveAll(routes);
            System.out.println("Nepali routes seeded successfully!");
        }
    }
}
