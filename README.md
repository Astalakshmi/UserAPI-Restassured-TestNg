# User API Automation Testing (TestNG + Rest Assured)

This repository contains automated test cases for the **User Management API**, which performs user CRUD operations: Create, Read, Update, and Delete. The tests validate API functionality, schema structure, and business logic using the **TestNG** framework and **Rest Assured** library in Java.

---

## Project Overview

The **User API** provides a RESTful interface to manage user data. This test suite uses **TestNG** and **Rest Assured** to validate:

- API endpoints
- Status codes
- Response body structure and values
- JSON Schema compliance
- Data-driven testing using Excel

---

##  Features Covered

- Create a new user (POST)
- Get user details by ID or name (GET)
- Full update of user (PUT)
- Partial update of user (PATCH)
- Delete user by ID or name (DELETE)
- Status code & response message validation
- JSON schema validation
- Logging using Log4j
- Data-driven testing using Excel (Apache POI)

---

## Tech Stack

- **Java 11+**
- **TestNG** – Testing framework
- **Rest Assured** – API testing
- **Apache POI** – Read/write Excel files for data-driven tests
- **Log4j** – Logging
- **Maven** – Dependency management
- **Allure / Extent Reports** – Test reporting (optional)
- **GitHub** – Source control

---

##  API Endpoints

| Method | Endpoint                                  | Description               |
|--------|-------------------------------------------|---------------------------|
| POST   | `/uap/createusers`                        | Create a new user         |
| GET    | `/uap/users`                              | Get all users             |
| GET    | `/uap/user/{id}`                          | Get user by ID            |
| GET    | `/uap/users/username/{userFirstName}`     | Get user by first name    |
| PUT    | `/user/updateUser/{userId}`               | Full update user          |
| PATCH  | `/uap/updateuserfields/{userId}`          | Partial update user       |
| DELETE | `/uap/deleteuser/{userId}`                | Delete user by ID         |
| DELETE | `/uap/deleteuser/username/{userFirstName}`| Delete user by name       |

---

##  Author

**Astalakshmi Amulraj** – QA Automation Engineer  
[GitHub](https://github.com/Astalakshmi) • [LinkedIn](https://www.linkedin.com/in/astaamul)
