# MedTrack Backend

MedTrack is a Spring Boot–based healthcare management system that handles appointments, patient records, doctor profiles, and medicine tracking. It uses PostgreSQL for persistence, Swagger for API documentation, and includes full test coverage.

---

## Features

- RESTful APIs for appointments, patients, doctors, and medicines  
- DTO-based request and response validation  
- Pagination, filtering, and date range search  
- Centralized exception handling  
- Swagger UI for interactive API documentation  
- Unit and controller tests using JUnit 5 and Mockito  
- PostgreSQL integration via Spring Data JPA  

---

## Technology Stack

| Component         | Technology           |
|-------------------|----------------------|
| Language          | Java 17              |
| Framework         | Spring Boot 3.1.5    |
| Database          | PostgreSQL           |
| API Documentation | Springdoc OpenAPI    |
| Testing           | JUnit 5, Mockito     |
| Build Tool        | Maven                |

---

## Project Structure

```
medtrack-backend/
├── src/
│   ├── main/
│   │   ├── java/com/medtrack/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   ├── dto/
│   │   │   └── exception/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/medtrack/
│           ├── service/
│           └── controller/
```

---

## API Documentation

Swagger UI is available at:

```
http://localhost:8080/swagger-ui/index.html
```

### Sample Endpoints

- `POST /appointments` – Create a new appointment  
- `GET /appointments/{id}` – Retrieve appointment by ID  
- `GET /appointments/search?patientName=Kishore&date=2025-09-28` – Filter by patient and date  
- `GET /appointments/range?from=2025-09-01&to=2025-09-30` – Search by date range  

---

## Testing

To execute all tests:

```bash
mvn test
```

Test coverage includes:

- `AppointmentServiceTest.java` – Service layer unit tests  
- `AppointmentControllerTest.java` – REST endpoint validation  

---

## Setup Instructions

1. Clone the repository:

```bash
git clone https://github.com/YOUR_USERNAME/medtrack-backend.git
```

2. Configure database credentials in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/medtrack
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Run the application:

```bash
mvn spring-boot:run
```

---

## Status

- All tests passing  
- Swagger UI operational  
- Backend ready for integration and deployment
  

---

## Author

Kishore  
Full Stack Java Developer  
Focused on clean architecture, robust validation, and production-grade API design.

