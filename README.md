Sure! Here's a clean, concise, and professional `README.md` file, focused only on practical setup and execution steps for your **DDD Microservices** project:

---

```markdown
# DDD Microservices with Spring Boot

This project demonstrates a microservices architecture using **Domain-Driven Design (DDD)** principles with **Spring Boot**, **Spring Cloud**, and **MySQL**. It includes service discovery using **Eureka**.

## Dependencies Used

- Spring Web  
- Spring Data JPA  
- Spring DevTools  
- Lombok  
- MySQL Driver  
- Eureka Discovery Client  
- Eureka Server (for service registry)

---

## Prerequisites

- Java 17+
- Maven
- MySQL Server
- IDE (IntelliJ / Eclipse)

---

## Setting Up the Project

### 1. Clone the Repository
```bash
git clone https://github.com/Vilsium/DDD--in-Real-life-Microservices.git
cd DDD--in-Real-life-Microservices
```

### 2. Import as Maven Project in Your IDE

---

## Database Configuration

Update the `application.yml` in each microservice:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/YOUR_DATABASE
    username: YOUR_USERNAME
    password: YOUR_PASSWORD
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL8Dialect
```

Create the corresponding MySQL databases beforehand.

---

## Running the Application

### Step 1: Start Eureka Server
```bash
cd eureka-server
mvn spring-boot:run
```

Visit `http://localhost:8761` to confirm it is running.

### Step 2: Start Other Microservices
For each microservice:
```bash
cd service-name
mvn spring-boot:run
```

Registered services will appear in the Eureka dashboard.

---

## Notes

- Ensure each microservice uses a unique `server.port`.
- All services must have Eureka client enabled and configured.

```yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

---

## Troubleshooting

- Make sure Eureka Server is running before starting microservices.
- Ensure MySQL is up and credentials are correct.
- Use different ports for each microservice to avoid conflicts.

---

## License

MIT License
```

---

Let me know if you'd like to include curl test examples, Postman collection links, or Docker support.
