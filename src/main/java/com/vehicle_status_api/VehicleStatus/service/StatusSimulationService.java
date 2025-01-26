package com.vehicle_status_api.VehicleStatus.service;

import com.vehicle_status_api.VehicleStatus.model.Status;
import com.vehicle_status_api.VehicleStatus.model.Vehicle;
import com.vehicle_status_api.VehicleStatus.repository.StatusRepository;
import com.vehicle_status_api.VehicleStatus.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

// The StatusSimulationService is responsible for simulating the vehicle status periodically
@Service
public class StatusSimulationService {

    // Injecting the VehicleRepository and StatusRepository beans into the service
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private StatusRepository statusRepository;

    // Scheduled task that runs every 60 seconds (1 minute)
    @Scheduled(fixedRate = 60000)  // 60000ms = 1 minute
    public void simulateVehicleStatus() {
        // Fetching all vehicles from the database
        List<Vehicle> vehicles = vehicleRepository.findAll();

        // Random object to simulate status
        Random random = new Random();

        // Iterating over each vehicle to simulate its status
        for (Vehicle vehicle : vehicles) {
            // Simulate random status change (either "CONNECTED" or "DISCONNECTED")
            String newStatus = random.nextBoolean() ? "CONNECTED" : "DISCONNECTED";

            // Checking if a status already exists for this vehicle
            Optional<Status> existingStatus = Optional.ofNullable(statusRepository.findLatestByVehicle(vehicle));

            if (existingStatus.isPresent()) {
                // If a status exists, update the status and lastUpdated timestamp
                Status status = existingStatus.get();
                status.setStatus(newStatus);
                status.setLastUpdated(LocalDateTime.now());
                // Save the updated status into the database
                statusRepository.save(status);
            } else {
                // If no status exists, create a new status record for the vehicle
                Status status = new Status();
                status.setVehicle(vehicle);  // Set the vehicle for the status
                status.setStatus(newStatus);  // Set the simulated status
                status.setLastUpdated(LocalDateTime.now());  // Set the current timestamp
                // Save the new status into the database
                statusRepository.save(status);
            }
        }
    }
}