# 🚗 Vehicle Management API

## 📋 Overview
This project is a RESTful API for managing vehicles and brands. It provides endpoints for CRUD operations, searching, and filtering vehicles based on various criteria.

## 🛠 Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- MapStruct
- Lombok
- MySQL

## 🔍 API Endpoints

### 🚙 Vehicle Endpoints
- **GET /api/vehicles/{id}** - Get vehicle by ID
- **POST /api/vehicles/add** - Add a new vehicle
- **PUT /api/vehicles/update/{id}** - Update an existing vehicle
- **DELETE /api/vehicles/delete/{id}** - Delete a vehicle by ID
- **GET /api/vehicles** - Get all vehicles
- **GET /api/vehicles/filtered** - Get vehicles with special filtering logic (price > 10M and brand starts with 'S', or price <= 10M and brand type is 'BUS')
- **GET /api/vehicles/filter** - Filter vehicles based on criteria (brand, year, price, owner)

## 📊 Data Models
- **Vehicle**: Represents a vehicle with properties like name, year of manufacture, price, owner, and brand.
- **Brand**: Represents a brand with properties like name and type.

## 🔄 Business Logic
- Vehicles can be filtered based on price, brand, year, and owner.
- Special filtering logic is applied for vehicles with price > 10M and brand starting with 'S', or price <= 10M and brand type is 'BUS'.
- Optional fields in vehicle data can be left empty, and the backend handles null values appropriately.

## 📜 License
This project is licensed under the MIT License.
