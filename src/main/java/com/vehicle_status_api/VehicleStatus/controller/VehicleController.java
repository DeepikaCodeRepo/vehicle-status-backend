package com.vehicle_status_api.VehicleStatus.controller;

import com.vehicle_status_api.VehicleStatus.DTO.VehicleDTO;
import com.vehicle_status_api.VehicleStatus.model.Customer;
import com.vehicle_status_api.VehicleStatus.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${allowed.origin}")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicles")
    public List<VehicleDTO> getVehicles(@RequestParam(required = false) Long customer) {
        return vehicleService.getVehicles(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return vehicleService.getAllCustomers();
    }
}