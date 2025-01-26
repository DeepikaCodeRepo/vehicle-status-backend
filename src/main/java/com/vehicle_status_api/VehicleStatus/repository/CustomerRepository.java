package com.vehicle_status_api.VehicleStatus.repository;
import com.vehicle_status_api.VehicleStatus.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

// The CustomerRepository interface extends JpaRepository to provide CRUD operations for the Customer entity
// JpaRepository provides built-in methods like save, delete, findById, findAll, etc.
// No additional methods are required here unless custom queries are needed.
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}