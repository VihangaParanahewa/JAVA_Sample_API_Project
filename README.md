# Java Sample API Project

This is a sample Spring Boot application designed to demonstrate basic CRUD operations for managing users. The project uses Spring Data JPA to interact with a MySQL database and exposes a RESTful API for managing users. The application is configured with Spring Boot 3.0 and uses the latest features of Java and Spring technologies.

## Features

- CRUD operations for users (Create, Read, Update, Delete)
- Input validation using annotations (`@Valid`, `@NotBlank`, `@NotNull`, etc.)
- Error handling using `@ExceptionHandler`
- H2 in-memory database for local development (can be switched to MySQL)
- Unit and integration tests for API endpoints
- Swagger UI integration for easy API documentation

## Technologies Used

- **Spring Boot 3.4.0**
- **Spring Data JPA**
- **MySQL Database**
- **JUnit 5**
- **Mockito**
- **Spring Security (optional)**

## Prerequisites

Before running the application, make sure you have the following installed:

- JDK 17 or higher
- Maven or Gradle
- MySQL (or use the H2 database for local development)
- IDE like IntelliJ IDEA or Eclipse (optional)

## Setup and Installation

### 1. Clone the repository

```bash
git clone https://github.com/VihangaParanahewa/JAVA_Sample_API_Project
cd JAVA_Sample_API_Project
```

### 2. Configure application.properties
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/sample_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
H2 Database
```bash
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Build the application
```bash
mvn clean install
```

## 4. Run the application
```bash
mvn spring-boot:run 
```
or 
```bash
java -jar target/your-app-name.jar
```

## Folder Structure
```bash
JAVA_Sample_API_Project/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── JAVA_Sample_API_Project/
│   │   │               ├── configuration/                # Configuration classes
│   │   │               │   └── AppConfig.java            # Application configuration class
│   │   │               ├── controller/                   # Controllers for the API
│   │   │               │   └── UserController.java       # UserController for managing user operations
│   │   │               ├── dto/                          # Data Transfer Objects (DTOs)
│   │   │               │   ├── UserCreateDTO.java        # DTO for creating a user
│   │   │               │   └── UserDTO.java              # DTO for user data
│   │   │               ├── entity/                       # Entity classes
│   │   │               │   └── User.java                 # User entity class mapped to the database
│   │   │               ├── exception/                    # Custom exception handlers
│   │   │               │   ├── APIException.java         # Custom APIException for error handling
│   │   │               │   ├── APIExceptionHandler.java  # Exception handler class
│   │   │               │   └── APIRequestException.java  # Custom exception for request errors
│   │   │               ├── repository/                   # Repositories for database interaction
│   │   │               │   └── UserRepository.java       # Repository interface for user data
│   │   │               ├── service/                      # Services for business logic
│   │   │               │   ├── UserService.java          # Interface for user service
│   │   │               │   └── impl/                     # Implementations for services
│   │   │               │       └── UserServiceImpl.java  # Implementation of UserService
│   │   │               └── Application.java              # Main Spring Boot application class
│   │   └── resources/
│   │       ├── application.properties                    # Database and application configuration
│   │       └── static/                                   # Static resources (CSS, JS, images)
│   │       └── templates/                                # Template files (for Thymeleaf, if used)
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── JAVA_Sample_API_Project/
│       │               ├── controller/                   # Tests for the controllers
│       │               │   └── UserControllerTest.java   # Unit tests for UserController
│       │               ├── service/                      # Tests for the services
│       │               │   └── UserServiceTest.java      # Unit tests for UserService
└── pom.xml                                               # Maven build file
└── README.md                                             # Project documentation
```

## API Endpoints

### 1. Get all users
```bash
GET /api/v1/users
```
#### Response :
```bash
[
    {
        "id": 1,
        "name": "John Doe",
        "address": "123 Main St"
    },
    {
        "id": 2,
        "name": "Jane Doe",
        "address": "456 Elm St"
    }
]
```

### 2. Create a new user
```bash
POST /api/v1/users
```
#### Response :
```bash
{
    "name": "John Doe",
    "address": "123 Main St"
}
```

### 3. Update an existing user
```bash
PUT /api/v1/users
```
#### Response :
```bash
{
    "id": 1,
    "name": "John Doe Updated",
    "address": "789 Oak St"
}
```

### 4. Delete a user
```bash
DELETE /api/v1/users/{id}
```
#### Response :
```bash
{
    "message": "User deleted successfully"
}
```

## Running Tests
```bash
mvn test
```

## Swagger UI
```bash
http://localhost:8080/swagger-ui/
```