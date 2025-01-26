// Package declaration for the Data Transfer Object (DTO) related to Vehicle
package com.vehicle_status_api.VehicleStatus.DTO;

// Importing necessary classes for the model (Status, Vehicle) and LocalDateTime handling
import com.vehicle_status_api.VehicleStatus.model.Status;
import com.vehicle_status_api.VehicleStatus.model.Vehicle;
import java.time.LocalDateTime;

// The VehicleDTO class represents the data transfer object for a vehicle
public class VehicleDTO {

    // Instance variables to store the vehicle's information
    private String vin;            // Vehicle Identification Number
    private String regNumber;      // Vehicle Registration Number
    private String customerName;   // Customer's name associated with the vehicle
    private String status;         // Current status of the vehicle
    private LocalDateTime lastUpdated;  // Timestamp for when the status was last updated

    // Constructor that initializes the VehicleDTO object based on a Vehicle object and a Status object
    public VehicleDTO(Vehicle vehicle, Status status) {
        this.vin = vehicle.getVin();  //VIN from the Vehicle object
        this.regNumber = vehicle.getRegNumber();  // Sets the registration number from the Vehicle object
        this.customerName = vehicle.getCustomer().getName();  // Sets the customer's name from the Vehicle object
        // status from the Status object, defaulting to "Unknown" if no status is provided
        this.status = (status != null) ? status.getStatus() : "Unknown";
        // last updated timestamp from the Status object, or null if no status is provided
        this.lastUpdated = (status != null) ? status.getLastUpdated() : null;
    }

    // Getters and Setters for all instance variables to allow access and modification of private fields
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