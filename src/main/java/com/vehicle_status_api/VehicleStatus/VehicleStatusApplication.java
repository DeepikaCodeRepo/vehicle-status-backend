package com.vehicle_status_api.VehicleStatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VehicleStatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleStatusApplication.class, args);
	}

}
