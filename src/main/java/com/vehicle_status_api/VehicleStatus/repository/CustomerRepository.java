package com.vehicle_status_api.VehicleStatus.repository;
import com.vehicle_status_api.VehicleStatus.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> { }
