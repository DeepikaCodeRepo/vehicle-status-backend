# Customer Vehicle Status Monitoring 

# Git Repo for API
https://github.com/DeepikaCodeRepo/vehicle-status-backend.git 

# Git Repo for front end
https://github.com/DeepikaCodeRepo/VehicleStatusGUI.git 

## Given scenario 
Our company has a number of connected vehicles that belongs to a number of customers.
We have a need to be able to view the status of the connection among these vehicles on a monitoring display.

The vehicles send the status of the connection one time per minute.
The status can be compared with a ping (network trace); no request from the vehicle means no connection.

Task:
Your task will be to create a data store that keeps these vehicles with their status and the customers who own them, as well as a GUI (web-based) that displays the status.
How the GUI is designed is up to you, as well as how you choose to implement the features and use suitable technologies.

Obviously, for this task, there are no real vehicles available that can respond to your "ping" request.
This can either be solved by using static values or ​​by creating a separate machinery that returns random fake status.

Requirement:
 A- Business Requirements:
	- Build API for the following:
		- List the vehicles along with its status.
		- Filter the vehicles for a specific customer.
	- Build a single page to display the vehicles along with the status consuming the API

Data:
Below you have all customers from the system; their addresses and the vehicles they own.

Save the information that you think is needed to solve the task above.
If you feel that databases and/or database design isn't something you are comfortable with, you're welcome to store the information in an object in the code.
`
| Kalles Grustransporter AB         |
| Cementvägen 8, 111 11 Södertälje  |
|-----------------------------------|
| VIN (VehicleId)       Reg. nr.    |
|-----------------------------------|
| YS2R4X20005399401     ABC123      |
| VLUR4X20009093588     DEF456      |
| VLUR4X20009048066     GHI789      |
|-----------------------------------|

| Johans Bulk AB                    |
| Balkvägen 12, 222 22 Stockholm    |
|-----------------------------------|
| VIN (VehicleId)       Reg. nr.    |
|-----------------------------------|
| YS2R4X20005388011     JKL012      |
| YS2R4X20005387949     MNO345      |
------------------------------------|

| Haralds Värdetransporter AB       |
| Budgetvägen 1, 333 33 Uppsala     |
|-----------------------------------|
| VIN (VehicleId)       Reg. nr.    |
|-----------------------------------|
| YS2R4X20005387765     PQR678      |
| YS2R4X20005387055     STU901      |
|-----------------------------------|
`

# Vehicle Status API - Backend Overview
This project provides a backend service for managing vehicle status information. The backend consists of a Spring Boot application that interacts with a MySQL database to store and retrieve vehicle data, statuses, and customer information.

# Key Features
Vehicle Management: Provides endpoints to get a list of vehicles and their status information.
Customer Management: Provides functionality to fetch all customer data.
Status Simulation: Simulates periodic updates of vehicle statuses (CONNECTED or DISCONNECTED).
## Components
### Controllers:
VehicleController: Exposes endpoints to fetch vehicles and customers.
Endpoints:
GET /api/vehicles: Retrieves a list of vehicles, optionally filtered by customer.
GET /api/customers: Retrieves a list of all customers. 

### DTO (Data Transfer Object):
VehicleDTO: Represents vehicle data along with the latest status.

### Models:
Vehicle: Represents vehicle data, including VIN, registration number, and the associated customer.
Customer: Represents customer data, including customer name and address.
Status: Represents the vehicle status (e.g., CONNECTED or DISCONNECTED), along with a timestamp for when it was last updated.
Repositories:

### Repositories
VehicleRepository: Interface to interact with the database for vehicle-related queries.
CustomerRepository: Interface for customer data access.
StatusRepository: Interface for status data access, including querying the latest status for a vehicle.
Services:

### Services
VehicleService: Contains business logic for retrieving vehicles and customers, and simulating vehicle statuses.
StatusSimulationService: Simulates vehicle status updates every minute and updates the database with the latest status for each vehicle.

### Simulation Logic
Status Simulation:
A scheduled task runs every minute to simulate random status updates for each vehicle (either "CONNECTED" or "DISCONNECTED").
The StatusSimulationService checks for the latest status of each vehicle, updating or inserting a new status record into the database.

### Vehicle and Customer Management:

The VehicleService handles retrieving vehicles based on customer ID, or all vehicles if no ID is provided. It also fetches the latest status for each vehicle and returns the data in a VehicleDTO format.
The VehicleController exposes endpoints to interact with the vehicle and customer data.

### Database Configuration
Database: MySQL
Update `application.properties`
spring.datasource.url=jdbc:mysql://localhost:3306/vehicle_monitoring
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update

### Create table schemas
### customer
`CREATE TABLE customer (
id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL
);`

### vehicle
`CREATE TABLE vehicle (
id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
vin VARCHAR(255) NOT NULL,
reg-number VARCHAR(255) NOT NULL,
customer_id BIGINT UNSIGNED NOT NULL,
FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);`

### status
`CREATE TABLE status (
id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
vehicle_id BIGINT UNSIGNED NOT NULL,
status VARCHAR(50) NOT NULL,
last-updated DATETIME DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (vehicle_id) REFERENCES vehicle(id) ON DELETE CASCADE
);`

## Insert sample data for test

## Insert Customers
`INSERT INTO customer (name, address) VALUES
('Kalles Grustransporter AB', 'Cementvägen 8, 111 11 Södertälje'),
('Johans Bulk AB', 'Balkvägen 12, 222 22 Stockholm'),
('Haralds Värdetransporter AB', 'Budgetvägen 1, 333 33 Uppsala');`

## Insert Vehicles
`INSERT INTO vehicle (vin, reg_number, customer_id) VALUES
('YS2R4X20005399401', 'ABC123', 1),
('VLUR4X20009093588', 'DEF456', 1),
('VLUR4X20009048066', 'GHI789', 1),
('YS2R4X20005388011', 'JKL012', 2),
('YS2R4X20005387949', 'MNO345', 2),
('YS2R4X20005387765', 'PQR678', 3),
('YS2R4X20005387055', 'STU901', 3);`


## Insert Statuses
`INSERT INTO status (vehicle_id, status, last_updated) VALUES
(1, 'Connected', NOW()),
(2, 'Disconnected', NOW()),
(3, 'Connected', NOW()),
(4, 'Disconnected', NOW()),
(5, 'Connected', NOW()),
(6, 'Disconnected', NOW()),
(7, 'Connected', NOW());`

### Technologies Used
Spring Boot: Framework for building the backend API.
Spring Data JPA: For database access and interaction.
MySQL: Database for storing vehicle, customer, and status information.
Spring Scheduling: For simulating vehicle status updates periodically.

### Setup Instructions and How to Run
Ensure you have MySQL installed and running.
Set up the database using the vehicle_monitoring database.
Clone the repository and build the Spring Boot application.
Configure the database settings in application.properties.
Run the Spring Boot application (mvn spring-boot:run or through your IDE).
Access the API via http://localhost:8080.

## Sample test result
### API End Points
![customer api.png](src/main/resources/customer%20api.png)
![vehicle API.png](src/main/resources/vehicle%20API.png)
![customer filter API.png](src/main/resources/customer%20filter%20API.png)

### DB data
![Customer table.png](src/main/resources/Customer%20table.png)
![vehicle table.png](src/main/resources/vehicle%20table.png)
![status table.png](src/main/resources/status%20table.png)