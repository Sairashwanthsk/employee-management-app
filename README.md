# 📘 Employee Management System (Spring Boot + PostgreSQL)

A simple **Employee Management REST API** built using Spring Boot, Spring Data JPA, and PostgreSQL.  
This project demonstrates CRUD operations, entity relationships, validation, and RESTful APIs.

---

# 🚀 Tech Stack

- Java 17+
- Spring Boot 3+
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- VS Code / GitHub Codespaces

---

# 📁 Project Structure

src/main/java/com/example/empmanagementapp

├── controller  
├── service  
├── repository  
├── entity  
├── exception  
└── EmpManAppApplication.java  

---

# 🧩 Features

## 👨‍💼 Employee Management
- Create employee
- Get all employees
- Get employee by ID
- Update employee
- Delete employee

## 🏢 Department Management
- Create department
- Get all departments

## 🔗 Relationships
- One Department → Many Employees
- JPA Foreign Key Mapping

## 🔍 Filtering
- Get employees by department
- Search using query parameters

---

# 🗄️ Database Design

## Department Table

| Column | Type | Constraints |
|--------|------|------------|
| id     | Long | Primary Key |
| name   | String | Unique, Not Null |

---

## Employee Table

| Column | Type | Constraints |
|--------|------|------------|
| id     | Long | Primary Key |
| name   | String | Not Null |
| email  | String | Unique |
| department_id | FK | References Department |

---

# 🔗 Sample API Endpoints

## 🏢 Department APIs

### Create Department
POST /api/departments

```json
{
  "name": "IT"
}
```


## Employee APIs
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

