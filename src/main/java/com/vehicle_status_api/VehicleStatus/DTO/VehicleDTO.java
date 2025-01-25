package com.vehicle_status_api.VehicleStatus.DTO;

import com.vehicle_status_api.VehicleStatus.model.Status;
import com.vehicle_status_api.VehicleStatus.model.Vehicle;

import java.time.LocalDateTime;

public class VehicleDTO {
    private String vin;
    private String regNumber;
    private String customerName;
    private String status;
    private LocalDateTime lastUpdated;

    public VehicleDTO(Vehicle vehicle, Status status) {
        this.vin = vehicle.getVin();
        this.regNumber = vehicle.getRegNumber();
        this.customerName = vehicle.getCustomer().getName();
        this.status = (status != null) ? status.getStatus() : "Unknown";
        this.lastUpdated = (status != null) ? status.getLastUpdated() : null;
    }

    // Getters and Setters
    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }

    public String getRegNumber() { return regNumber; }
    public void setRegNumber(String regNumber) { this.regNumber = regNumber; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}