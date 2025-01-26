package com.vehicle_status_api.VehicleStatus.repository;
import com.vehicle_status_api.VehicleStatus.model.Status;

import com.vehicle_status_api.VehicleStatus.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// The StatusRepository interface extends JpaRepository to provide CRUD operations for the Status entity
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    // Custom query to retrieve the most recent status for a given vehicle
    @Query("SELECT s FROM Status s WHERE s.vehicle = :vehicle ORDER BY s.lastUpdated DESC")
    // The @Param annotation binds the vehicle parameter to the query
    Status findLatestByVehicle(@Param("vehicle") Vehicle vehicle);
}