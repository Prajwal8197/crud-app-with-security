# Spring Boot 4 + JWT Security & Product Management System

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.0-brightgreen?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring_Security-6.x-blue?style=for-the-badge&logo=springsecurity)
![JWT](https://img.shields.io/badge/JWT-0.12.5-black?style=for-the-badge&logo=jsonwebtokens)

A robust, enterprise-grade Role-Based Access Control (RBAC) system built using the latest **Spring Boot 4.0.0** and **Java 21**. This project demonstrates a stateless architecture for managing users and an inventory of products with multi-role authorization logic.



## 🚀 Key Features

- **Stateless Authentication:** Implements JWT-based flow using the latest **JJWT 0.12.5** fluent API (`verifyWith`, `parseSignedClaims`).
- **Advanced RBAC:** Supports four distinct roles via Enums: `ADMIN`, `MANAGER`, `USER`, and `GUEST`.
- **Method-Level Security:** Fine-grained access control using `@EnableMethodSecurity` and `@PreAuthorize`.
- **Optimized Configuration:** Decoupled `ApplicationConfig` and `SecurityConfig` to resolve circular dependencies and adhere to the Single Responsibility Principle.
- **H2 In-Memory Database:** Real-time persistence with H2 Console access enabled (Frame Options and CSRF handled).
- **Automated Data Seeding:** A `CommandLineRunner` implementation ensures a default Admin account is created on system startup.

## 🛠 Tech Stack

- **Language:** Java 21 (LTS)
- **Framework:** Spring Boot 4.0.0 (Web, Data JPA, Security)
- **Token Management:** JJWT (Java JWT) 0.12.5
- **Database:** H2 (In-Memory)
- **ORM:** Hibernate (via Spring Data JPA)
- **Tooling:** Lombok, Maven



## 🔐 Security Architecture

The application implements a custom security filter chain that intercepts every request:
1. **`JwtAuthFilter`**: Validates the `Authorization: Bearer <token>` header.
2. **`CustomUserDetailsService`**: Loads user entities and wraps them in a `UserDetails` implementation.
3. **`CustomUserDetails`**: Safely maps DB roles (Enum) to Spring Security `GrantedAuthority` using the `.name()` method.



## 🚦 API Endpoints & Permissions

### Authentication (Public)
| Endpoint | Method | Description |
| :--- | :--- | :--- |
| `/auth/register` | POST | Public registration. Automatically assigns `ROLE_USER`. |
| `/auth/login` | POST | Authenticates credentials and returns a JWT. |

### Product Management (Protected)
| Endpoint | Method | Required Role | Functionality |
| :--- | :--- | :--- | :--- |
| `/products/all` | GET | `USER`, `MANAGER`, `ADMIN` | List all inventory products. |
| `/products/add` | POST | `MANAGER`, `ADMIN` | Create a new product entry. |
| `/products/{id}` | GET | `USER` | Fetch specific product details. |

## ⚙️ Installation & Setup

1. **Clone the Project:**
   ```bash
   git clone https://github.com/Prajwal8197/crud-app-with-security.git
   cd crud-app-with-security