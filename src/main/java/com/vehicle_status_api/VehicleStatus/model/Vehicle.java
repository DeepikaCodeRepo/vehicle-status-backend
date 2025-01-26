package com.vehicle_status_api.VehicleStatus.model;

import jakarta.persistence.*;


@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vin;
    private String regNumber;

    // Many-to-One relationship with the Customer entity, indicating that each vehicle is related to one customer
    @ManyToOne
    @JoinColumn(name = "customer_id") // foreign key column
    private Customer customer;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }

    public String getRegNumber() { return regNumber; }
    public void setRegNumber(String regNumber) { this.regNumber = regNumber; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}