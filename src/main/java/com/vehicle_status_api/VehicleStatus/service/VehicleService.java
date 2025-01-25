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

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<VehicleDTO> getVehicles(Long customerId) {
        List<Vehicle> vehicles = (customerId != null)
                ? vehicleRepository.findByCustomerId(customerId)
                : vehicleRepository.findAll();

        return vehicles.stream()
                .map(vehicle -> {
                    Status latestStatus = statusRepository.findLatestByVehicle(vehicle);
                    return new VehicleDTO(vehicle, latestStatus);
                })
                .collect(Collectors.toList());
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}