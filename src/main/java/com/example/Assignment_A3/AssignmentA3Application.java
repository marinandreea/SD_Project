package com.example.Assignment_A3;

import com.example.Assignment_A3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class AssignmentA3Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentA3Application.class, args);
	}

	@Autowired
	private OrderService orderService;


	@Override
	public void run(String... args) throws Exception {
		orderService.getAllOrders();
	}
}
