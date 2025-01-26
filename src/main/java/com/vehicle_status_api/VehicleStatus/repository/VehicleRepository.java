package com.vehicle_status_api.VehicleStatus.repository;
import com.vehicle_status_api.VehicleStatus.model.Vehicle;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// The VehicleRepository interface extends JpaRepository to provide CRUD operations for the Vehicle entity
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // Custom method to find vehicles by customer ID
    // This is automatically implemented method by Spring Data JPA based on the method name
    List<Vehicle> findByCustomerId(Long customerId);
}
