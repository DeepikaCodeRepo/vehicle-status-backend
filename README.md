# vehicle-status-monitoring

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


## **Project Structure**

## Environment
Java 17
Maven

### **Backend (Spring Boot)**
- **Modules:**
  - `controller`: Handles API requests.
  - `service`: Business logic for vehicle management.
  - `repository`: Interfaces for interacting with the database.
  - `model`: Entity classes for database mapping.
  - `dto`: Data Transfer Objects.

### **Frontend (ReactJS)**
- **Structure:**
  - Components: Reusable components for the UI.
  - Pages: Views rendered based on routes.
  - Services: Axios calls to the backend.

---

## **Setup Instructions**

### **Backend**

1. Clone the repository.
2. Navigate to the backend directory.
3. Update `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/vehicle_monitoring
   spring.datasource.username=YOUR_DB_USERNAME
   spring.datasource.password=YOUR_DB_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Build and run the application:
   ```bash
   ./mvn spring-boot:run
   ```
5. API Endpoints:
   - `GET /api/vehicles`: List all vehicles with statuses.
   - `GET /api/vehicles?customer={customerId}`: Filter by customer.
   - `GET /api/customers`: List all customers.
  
# Database
## Create table schemas
## customer
CREATE TABLE customer (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

## vehicle
CREATE TABLE vehicle (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    vin VARCHAR(255) NOT NULL,
    reg-number VARCHAR(255) NOT NULL,
    customer_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

## status
CREATE TABLE status (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    vehicle_id BIGINT UNSIGNED NOT NULL,
    status VARCHAR(50) NOT NULL,
    last-updated DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id) ON DELETE CASCADE
);

## Insert sample data for test

## Insert Customers
INSERT INTO customer (name, address) VALUES
('Kalles Grustransporter AB', 'Cementvägen 8, 111 11 Södertälje'),
('Johans Bulk AB', 'Balkvägen 12, 222 22 Stockholm'),
('Haralds Värdetransporter AB', 'Budgetvägen 1, 333 33 Uppsala');

## Insert Vehicles
INSERT INTO vehicle (vin, reg_number, customer_id) VALUES
('YS2R4X20005399401', 'ABC123', 1),
('VLUR4X20009093588', 'DEF456', 1),
('VLUR4X20009048066', 'GHI789', 1),
('YS2R4X20005388011', 'JKL012', 2),
('YS2R4X20005387949', 'MNO345', 2),
('YS2R4X20005387765', 'PQR678', 3),
('YS2R4X20005387055', 'STU901', 3);


## Insert Statuses
INSERT INTO status (vehicle_id, status, last_updated) VALUES
(1, 'Connected', NOW()),
(2, 'Disconnected', NOW()),
(3, 'Connected', NOW()),
(4, 'Disconnected', NOW()),
(5, 'Connected', NOW()),
(6, 'Disconnected', NOW()),
(7, 'Connected', NOW());

## Sample test result

![Screenshot 2025-01<img width="399" alt="Screenshot 2025-01-25 at 19 57 22" src="https://github.com/user-attachments/assets/3c4cd681-d1e4-405c-98e1-0a05a7f1b47d" />
-25 at 19 56 23](https://github.com/user-attachments/assets/5aebeaf5-8742-47bc-8e81-417d63c8327d)

<img width="412" alt="Screenshot 2025-01-25 at 19 55 53" src="https://github.com/user-attachments/assets/d2f473db-3774-4691-bce7-b447a1b84419" />

<img widt![Screenshot 2025-01-25 at 19 55 22](https://github.com/user-attachments/assets/5b790cc2-df05-4f57-9705-e36acfe053f5)
h="384" alt="Screenshot 2025-01-25 at 19 50 18" src="https://github.com/user-attachments/assets/342f70dc-de72-45fe-9233-ed271b29707e" />
