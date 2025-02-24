# Group Expense Calculator

This project is a group expense calculator application. The application efficiently handles group transactions using Spring Boot Microservices and Mysql for data storage.

## Problem Statement

Keeping track of shared expenses can be difficult. This app helps you record payments, figure out who owes money, and easily settle up all expenses with one click. 

  Records transactions involving multiple users.
  Calculates the final amounts that each user owes or is owed.
  Simplifies the settlement process so that all pending transactions can be cleared with a single action.

  ## Overview
  
  This application is built using Spring Boot and follows a microservices architecture. The solution is divided into two main services:

  * User Service: Manages user data and provides REST endpoints for user management. 
  * Transaction Service: Handles transactions, calculates the owed and receivable amounts and finalizes settlements.

## Features

 In this design, we split the responsibilities into two microservices:
 
 ### User Service 
 Manages User Data

### Transaction Service 
 Manages transactions between users and calculates who owes or should receive money, as well as settles transactions.


## Technologies Used

- Spring Boot
- Spring Data JPA
- Thymeleaf (for server-side views)
- Feign Client (for inter-service communication)
- Eureka (optional, for service discovery)
- MySQL/PostgreSQL (database)

## Getting Started


### Clone the Repository

```bash
git clone https://github.com/Bhargava-Naidu/group-expense-calculator.git
cd group-expense-calculator
```

### Build the Project

```bash
mvn clean install
```

## Run the Microservices
### Start the User Service
```bash
cd user-service
mvn spring-boot:run
```
### Start the transaction service
```bash
cd ../transaction-service
mvn spring-boot:run
```
### Access the Application
  - User Service (web interface): http://localhost:8080/users/show
  - Transaction Service (settlement dashboard): http://localhost:8081/transactions/settlement
  - Access the Swagger UI for API documentation: http://localhost:8080/swagger-ui.html,  http://localhost:8081/swagger-ui.html
    
### Feedback and Improvements

Your feedback is valuable. If you have any suggestions or improvements, please feel free to reach out.

