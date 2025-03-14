package com.infinite.onlineTicket;

import com.infinite.onlineTicket.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//		(exclude = {SecurityAutoConfiguration.class})
//@EnableJpaRepositories(basePackages = "com.infinite.onlineTicket.repository")
public class OnlineTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTicketApplication.class, args);
	}

}
