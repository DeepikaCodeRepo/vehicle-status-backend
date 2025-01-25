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

@Service
public class StatusSimulationService {
    //private static final String[] STATUSES = {"Connected", "Disconnected"};
    //private static final Random random = new Random();

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private StatusRepository statusRepository;

    // Scheduled to run every minute
    @Scheduled(fixedRate = 60000)  // 60000ms = 1 minute
    public void simulateVehicleStatus() {
        List<Vehicle> vehicles = vehicleRepository.findAll();  // Fetch all vehicles
        Random random = new Random();

        for (Vehicle vehicle : vehicles) {
            // Simulate random status change (either Connected or Disconnected)
            //String status = STATUSES[random.nextInt(STATUSES.length)];
            String newStatus = random.nextBoolean() ? "CONNECTED" : "DISCONNECTED";

            // Check if a status record already exists for this vehicle
            Optional<Status> existingStatus = Optional.ofNullable(statusRepository.findLatestByVehicle(vehicle));

            if (existingStatus.isPresent()) {
                // Update the existing status
                Status status = existingStatus.get();
                status.setStatus(newStatus);
                status.setLastUpdated(LocalDateTime.now());
                statusRepository.save(status);
            } else {
                // Insert a new status
                Status status = new Status();
                status.setId(vehicle.getId());
                status.setStatus(newStatus);
                status.setLastUpdated(LocalDateTime.now());
                statusRepository.save(status);
            }

            // Create a new Status entry for each vehicle
            //Status newStatus = new Status();
            //newStatus.setVehicle(vehicle);
            //newStatus.setStatus(status);
            //newStatus.setLastUpdated(LocalDateTime.now());

            // Save the new status into the database
            //statusRepository.save(newStatus);
        }
    }
}

