# 📘 Employee Management System (Spring Boot + PostgreSQL + Docker)

A production-ready **Employee Management REST API** built using Spring Boot, featuring secure authentication, clean architecture and containerized deployment using Docker and Docker compose.

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot 3+
- Spring Web
- Spring Data JPA
- Spring Security (JWT)
- PostgreSQL
- Maven
- Swagger (OpenAPI)
- Docker & Docker Compose
- VS Code / GitHub Codespaces

---

## 📁 Project Structure

src/main/java/com/example/empmanagementapp

├── controller  
├── service  
├── repository  
├── entity   
├── dto   
├── mapper   
├── exception  
├── security   
└── EmpManAppApplication.java  

---

## 🧩 Features

- Employee CRUD operations
- Department management
- One-to-Many relationship mapping
- Filtering & pagination
- JWT-based authentication
- DTO & Mapper architecture
- Global exception handling
- AOP logging

---

## 🗄️ Database Design

### Department Table

| Column | Type   | Constraints      |
|--------|--------|------------------|
| id     | Long   | Primary Key      |
| name   | String | Unique, Not Null |

---

### Employee Table

| Column        | Type   | Constraints           |
|---------------|--------|-----------------------|
| id            | Long   | Primary Key           |
| name          | String | Not Null              |
| email         | String | Unique                |
| department_id | FK     | References Department |

---

### Users Table

| Column     | Type   | Constraints      |
|------------|--------|------------------|
| id         | Long   | Primary Key      |
| username   | String | Unique, Not Null |
| password   | String | Not Null         |

---

## Docker Setup

Run:
docker-compose up --build

Access:
http://localhost:8080
http://localhost:8080/swagger-ui/index.html


---

## Authentication

POST /api/auth/login
{
  "username": "admin",
  "password": "admin
}

Use:
Authorization: Bearer <token>


## 🔗 Sample API Endpoints

### 🏢 Department APIs

### Create Department
POST /api/departments

```json
{
  "name": "IT"
}
```


### Employee APIs

### Create Employee
POST /api/employees

```json
{
  "name": "Sai",
  "email": "sai@gmail.com",
  "department": {
    "id": 1
  }
}
```

## 📌 Future Improvements
- Unit Testing
- CI/CD pipeline
- Kubernetes deployment


## 👨‍💻 Author
Built for learning Spring Boot backend development 🚀

