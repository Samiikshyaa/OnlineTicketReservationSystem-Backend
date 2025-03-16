package com.infinite.onlineTicket.service.dataSeeder;

import com.infinite.onlineTicket.model.Route;
import com.infinite.onlineTicket.repository.RouteRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class RouteInit {
//    implements CommandLineRunner -> this is run before hibernate creates table

    private final RouteRepository routeRepository;

    @PostConstruct
    public void seedRoutes() {
        if (routeRepository.count() == 0) {
            List<Route> routes = List.of(
                    new Route(null, "Kathmandu", "Pokhara", LocalDate.parse("2025-04-30"), LocalTime.parse("07:00"), 1200.0, 100.0),
                    new Route(null, "Kathmandu", "Chitwan", LocalDate.parse("2025-05-01"), LocalTime.parse("06:30"), 600.0, 50.0),
                    new Route(null, "Kathmandu", "Biratnagar", LocalDate.parse("2025-05-02"), LocalTime.parse("08:00"), 1800.0, 150.0),
                    new Route(null, "Pokhara", "Butwal", LocalDate.parse("2025-05-03"), LocalTime.parse("09:30"), 500.0, 40.0),
                    new Route(null, "Birgunj", "Kathmandu", LocalDate.parse("2025-05-04"), LocalTime.parse("10:00"), 1300.0, 100.0),
                    new Route(null, "Dhangadhi", "Nepalgunj", LocalDate.parse("2025-05-05"), LocalTime.parse("05:45"), 900.0, 80.0),
                    new Route(null, "Kathmandu", "Dharan", LocalDate.parse("2025-05-06"), LocalTime.parse("07:15"), 1600.0, 120.0),
                    new Route(null, "Biratnagar", "Janakpur", LocalDate.parse("2025-05-07"), LocalTime.parse("06:00"), 800.0, 60.0),
                    new Route(null, "Pokhara", "Lumbini", LocalDate.parse("2025-05-08"), LocalTime.parse("07:45"), 700.0, 50.0)
            );

            routeRepository.saveAll(routes);
            System.out.println("Routes seeded successfully!");
        }
    }
}
