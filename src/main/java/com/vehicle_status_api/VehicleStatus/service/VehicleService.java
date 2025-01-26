package com.vehicle_status_api.VehicleStatus.service;

import com.vehicle_status_api.VehicleStatus.DTO.VehicleDTO;
import com.vehicle_status_api.VehicleStatus.model.Customer;
import com.vehicle_status_api.VehicleStatus.model.Status;
import com.vehicle_status_api.VehicleStatus.model.Vehicle;
import com.vehicle_status_api.VehicleStatus.repository.CustomerRepository;
import com.vehicle_status_api.VehicleStatus.repository.StatusRepository;
import com.vehicle_status_api.VehicleStatus.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// The VehicleService class for handling vehicle related logics
@Service
public class VehicleService {

    // Injecting the VehicleRepository, StatusRepository, and CustomerRepository into the service
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Method to get a list of vehicles for a specific customer (or all vehicles if no customerId is provided)
    public List<VehicleDTO> getVehicles(Long customerId) {
        // Fetching the vehicles based on customerId if provided, otherwise fetch all vehicles
        List<Vehicle> vehicles = (customerId != null)
                ? vehicleRepository.findByCustomerId(customerId)  // If customerId is provided, fetch vehicles for that customer
                : vehicleRepository.findAll();  // Otherwise, fetch all vehicles

        // Mapping the list of vehicles to a list of VehicleDTO objects
        return vehicles.stream()
                .map(vehicle -> {
                    // Fetching the latest status for each vehicle
                    Status latestStatus = statusRepository.findLatestByVehicle(vehicle);
                    // Creating a new VehicleDTO object and returning it
                    return new VehicleDTO(vehicle, latestStatus);
                })
                .collect(Collectors.toList());  // Collecting the mapped results into a list
    }

    // Method to get all customers from the repository
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();  // Fetching all customers
    }
}