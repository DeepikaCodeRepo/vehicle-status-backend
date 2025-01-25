package com.vehicle_status_api.VehicleStatus.repository;
import com.vehicle_status_api.VehicleStatus.model.Status;

import com.vehicle_status_api.VehicleStatus.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query("SELECT s FROM Status s WHERE s.vehicle = :vehicle ORDER BY s.lastUpdated DESC")
    Status findLatestByVehicle(@Param("vehicle") Vehicle vehicle);
}
