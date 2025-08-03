#  Support Ticket Management API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

This project is a basic **Ticket Management System** API developed using Spring Boot and Spring Security. It includes JWT-based authentication and role-based (user/administrator) access control.

---

##  Table of Contents

- [Features](#-features)
- [Technologies Used](#-technologies-used)
- [Getting Started](#-getting-started)
- [Usage](#-usage)
- [API Endpoints](#-api-endpoints)
- [Configuration](#️-configuration)
- [Contributing](#-contributing)
- [License](#-license)

---

##  Features

- **User Registration & Login**: Secure user authentication using JWT.
- **Ticket Creation**: Authenticated users can create new support tickets.
- **List User's Tickets**: Users can view a list of their own submitted tickets.
- **List All Tickets**: Administrators can view a complete list of all tickets in the system.
- **Role-Based Access Control**: Secure endpoints using `@PreAuthorize` for 'USER' and 'ADMIN' roles.
- **DTO Mapping**: Clean and efficient data transfer using ModelMapper.
- **JPA & Hibernate**: Robust data persistence with Spring Data JPA and Hibernate.

---

##  Technologies Used

- **Framework**: Spring Boot 3.x
- **Security**: Spring Security (JWT-based)
- **Language**: Java 17
- **Data**: Spring Data JPA, Hibernate
- **Database**: PostgreSQL (or H2 for testing)
- **Utilities**: Lombok, ModelMapper
- **Build Tool**: Maven

---
