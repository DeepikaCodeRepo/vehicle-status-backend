package com.vehicle_status_api.VehicleStatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// The VehicleStatusApplication class is the entry point for the Spring Boot application
@SpringBootApplication  // This annotation marks the class as the main Spring Boot application class
@EnableScheduling       // This annotation enables scheduled tasks within the application
public class VehicleStatusApplication {

	// The main method to run the Spring Boot application
	public static void main(String[] args) {
		// Runs the Spring Boot application
		SpringApplication.run(VehicleStatusApplication.class, args);
	}
}
