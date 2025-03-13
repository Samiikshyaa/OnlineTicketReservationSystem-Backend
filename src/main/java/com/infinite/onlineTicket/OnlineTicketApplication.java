package com.infinite.onlineTicket;

import com.infinite.onlineTicket.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.infinite.onlineTicket.repository")
public class OnlineTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTicketApplication.class, args);
	}

}
