// Package declaration - defines the package for this class
package com.vehicle_status_api.VehicleStatus.controller;

// Importing necessary classes for DTO, model, and service functionalities
import com.vehicle_status_api.VehicleStatus.DTO.VehicleDTO;
import com.vehicle_status_api.VehicleStatus.model.Customer;
import com.vehicle_status_api.VehicleStatus.service.VehicleService;

// Importing necessary Spring annotations for controller functionality
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marking this class as a REST Controller to handle HTTP requests
@RestController
// Mapping the base URI for the controller's endpoints
@RequestMapping("/api")
// Enabling Cross-Origin Resource Sharing (CORS) for specific origins
@CrossOrigin(origins = "${allowed.origin}")
public class VehicleController {

    // Injecting the VehicleService class to handle vehicle-related business logic
    @Autowired
    private VehicleService vehicleService;

    // Endpoint to get a list of vehicles, with an optional customer query parameter
    @GetMapping("/vehicles")
    public List<VehicleDTO> getVehicles(@RequestParam(required = false) Long customer) {
        // Calls the service method to retrieve vehicles, passing the customer ID if provided
        return vehicleService.getVehicles(customer);
    }

    // Endpoint to get a list of all customers
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        // Calls the service method to retrieve all customers
        return vehicleService.getAllCustomers();
    }
}