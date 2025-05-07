
# 🚗 Vehicle Management REST API

A simple Spring Boot RESTful API for managing vehicles, their owners, and associated brands.

---

## 📦 Base URL

```
/api/vehicles
```

---

## 📚 Endpoints

### 🔍 Get Vehicle by ID

```
GET /api/vehicles/{id}
```

- **Description**: Retrieve vehicle details by ID.
- **Response**: `VehicleResponse`
- **Status**: `200 OK`, or `404 Not Found` if not found.

---

### 📋 Get All Vehicles

```
GET /api/vehicles
```

- **Description**: Fetch a list of all vehicles.
- **Response**: List of `VehicleResponse`
- **Status**: `200 OK`

---

### ➕ Add a New Vehicle

```
POST /api/vehicles/add
```

- **Description**: Add a new vehicle. If the brand does not exist, it will be created automatically.
- **Request Body**: `VehicleRequest`
- **Response**: ID of the newly created vehicle
- **Status**: `201 Created`

---

### ✏️ Update a Vehicle

```
PUT /api/vehicles/update/{id}
```

- **Description**: Update an existing vehicle.
- **Request Body**: `VehicleRequest`
- **Response**: ID of the updated vehicle
- **Status**: `200 OK`, or `404 Not Found` if not found.

---

### ❌ Delete a Vehicle

```
DELETE /api/vehicles/delete/{id}
```

- **Description**: Delete a vehicle by ID.
- **Response**: ID of the deleted vehicle
- **Status**: `200 OK`, or `404 Not Found` if not found.

---

### 🔎 Filter by Brand Name

```
GET /api/vehicles/brands?brandName={brandName}
```

- **Description**: Get vehicles by brand name.
- **Response**: List of `VehicleResponse`
- **Status**: `200 OK`

---

### 📅 Filter by Year of Manufacture

```
GET /api/vehicles/years?year={year}
```

- **Description**: Get vehicles manufactured in a specific year.
- **Response**: List of `VehicleResponse`
- **Status**: `200 OK`

---

### 💰 Filter by Price Range

```
GET /api/vehicles/prices?min={min}&max={max}
```

- **Description**: Get vehicles within a price range.
- **Response**: List of `VehicleResponse`
- **Status**: `200 OK`

---

### 👤 Filter by Owner

```
GET /api/vehicles/owner?name={ownerName}
```

- **Description**: Get vehicles by owner name (case-insensitive).
- **Response**: List of `VehicleResponse`
- **Status**: `200 OK`

---

### 🎯 Special Filter

```
GET /api/vehicles/filtered
```

- **Description**: Custom filter logic:
  - Return vehicles with price > 10,000,000 **AND** brand name starting with "S"
  - OR vehicles with price <= 10,000,000 **AND** brand type is "BUS"
- **Response**: List of `VehicleResponse`
- **Status**: `200 OK`

---

## 📘 Models

### VehicleRequest

```json
{
  "name": "string",
  "owner": "string",
  "yearOfManufacture": 2020,
  "price": 15000000,
  "brandName": "Toyota"
}
```

### VehicleResponse

```json
{
  "name": "string",
  "owner": "string",
  "yearOfManufacture": 2020,
  "price": 15000000,
  "created": "2024-05-01T10:15:30Z"
}
```

---

## 🛠 Technologies

- Java 17+
- Spring Boot
- Spring Data JPA
- H2/PostgreSQL/MySQL (you choose)
- Lombok

---

## 🧪 Test and Run

- Run the project using your IDE or `mvn spring-boot:run`
- Access Swagger UI (if integrated) or use Postman to test endpoints.

---

## 📌 Notes

- Brand is automatically created if it does not exist when adding or updating a vehicle.
- All filters return `200 OK` with an empty list if no data matches.
- `created` timestamp is auto-generated.

---

## 🧑‍💻 Author

Made with ❤️ by Hi Truong
