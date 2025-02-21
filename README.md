# API Authorization Service

A Spring Boot service that handles authentication and authorization for the e-commerce microservices ecosystem.

## Features

- User authentication with JWT tokens
- Role-based authorization
- Secure password handling
- RESTful API endpoints
- Swagger/OpenAPI documentation

## Technologies

- Java 17
- Spring Boot 3.x
- Spring Security
- MySQL Database
- JWT (JSON Web Tokens)
- Maven
- Swagger/OpenAPI 3.0

## Prerequisites

- JDK 17 or higher
- Maven 3.x
- MySQL 8.x
- Git

## Getting Started

1. Clone the repository:
git clone [repository-url]

2. Configure MySQL database:
   
   - Create a database named ecommerce
   - Update database credentials in application.properties if needed
3. Build the project:
mvn clean install

4. Run the application:
mvn spring-boot:run

The service will start on port 8081.

## API Documentation
Access the Swagger UI documentation at:

http://localhost:8081/swagger-ui.html

## API Endpoints
### Authentication
- POST /api/v1/auth/login - Authenticate user and get JWT token
## Configuration
Key configuration properties in application.properties :

- server.port : 8081
- spring.datasource.url : JDBC URL for MySQL database
- jwt.expiration : JWT token expiration time (24 hours)
## Testing
Run the tests using:
mvn test

## Security
- JWT-based authentication
- Password encryption using BCrypt
- Role-based access control
## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request
## License
[Add your license information here]

This README provides a comprehensive overview of your project, including setup instructions, features, and technical details. You may want to customize it further by:

1. Adding your specific license information
2. Including your actual repository URL
3. Adding any specific deployment instructions
4. Including any additional endpoints or features
5. Adding contact information or contribution guidelines
6. Including any environment-specific setup requirements