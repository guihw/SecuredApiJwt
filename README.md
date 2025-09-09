# Spring Boot JWT Authentication API

A secure REST API built with Spring Boot that implements JWT (JSON Web Token) authentication for protecting endpoints.

## Features

- JWT-based authentication
- Stateless session management
- Role-based access control
- Secure password encoding (BCrypt)
- Protected and public endpoints
- Custom JWT filter for token validation

## Technologies Used

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Security 6.x**
- **JWT (JSON Web Tokens)**
- **Maven**

## Project Structure

```
src/main/java/com/guilh/SecuredApiJWT/
├── Controller/
│   ├── AuthController.java      # Authentication endpoints
│   └── CustomerController.java  # Protected endpoints
├── config/
│   └── SecurityConfig.java      # Security configuration
├── filter/
│   └── JwtAuthFilter.java       # JWT authentication filter
├── service/
│   ├── JwtService.java          # JWT token operations
│   └── MyUserDetailsService.java # User details service
└── SecuredApiJwtApplication.java # Main application class
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- Your favorite IDE (IntelliJ IDEA, Eclipse, VS Code)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/SecuredApiJWT.git
   cd SecuredApiJWT
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

## API Endpoints

### Public Endpoints (No Authentication Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/login` | Authenticate user and get JWT token |
| GET | `/auth/hello` | Public hello endpoint |

### Protected Endpoints (JWT Token Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/hello` | Protected hello endpoint for authenticated users |

## Authentication Flow

### 1. Login and Get JWT Token

**Request:**
```bash
POST /auth/login
Content-Type: application/x-www-form-urlencoded

username=user&password=password
```

**Response:**
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzMxMDY4NDAwLCJleHAiOjE3MzEwNzIwMDB9...
```

### 2. Access Protected Endpoints

**Request:**
```bash
GET /api/hello
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzMxMDY4NDAwLCJleHAiOjE3MzEwNzIwMDB9...
```

**Response:**
```
Hello Authenticated user!
```

## Testing with Postman

### Step 1: Get JWT Token
1. Create a new POST request to `http://localhost:8080/auth/login`
2. In the Body tab, select `x-www-form-urlencoded`
3. Add parameters:
   - `username`: `user`
   - `password`: `password`
4. Send the request and copy the returned JWT token

### Step 2: Access Protected Endpoint
1. Create a new GET request to `http://localhost:8080/api/hello`
2. In the Authorization tab, select "Bearer Token"
3. Paste the JWT token from Step 1
4. Send the request

## Default User Credentials

For testing purposes, the application includes a hardcoded user:

- **Username:** `user`
- **Password:** `password`
- **Role:** `USER`

> ⚠️ **Note:** In production, replace the hardcoded user with a proper user management system and database.

## Configuration

### JWT Configuration

The JWT configuration can be found in `JwtService.java`:

- **Secret Key:** Used for signing tokens (should be externalized in production)
- **Token Expiration:** 1 hour (3600000 ms)
- **Algorithm:** HMAC SHA-256

### Security Configuration

Key security settings in `SecurityConfig.java`:

- **Session Management:** Stateless (JWT-based)
- **CSRF:** Disabled (appropriate for APIs)
- **Public Endpoints:** `/auth/**`
- **Protected Endpoints:** All other endpoints require authentication

## Security Considerations

### For Production Use:

1. **Externalize JWT Secret Key**
   ```properties
   # application.properties
   app.jwt.secret=${JWT_SECRET:your-secret-key}
   app.jwt.expiration=${JWT_EXPIRATION:3600000}
   ```

2. **Use Environment Variables**
   ```bash
   export JWT_SECRET=your-very-long-and-secure-secret-key
   export JWT_EXPIRATION=3600000
   ```

3. **Implement Proper User Management**
   - Replace hardcoded users with database storage
   - Add user registration endpoint
   - Implement password reset functionality

4. **Add Input Validation**
   - Validate request parameters
   - Implement rate limiting
   - Add request/response logging


## Common Issues and Solutions

### Issue: 403 Forbidden on Protected Endpoints
**Solution:** Ensure you're using `@RestController` instead of `@Controller` in your controllers.

### Issue: JWT Token Not Working
**Solution:** 
- Check that the token is properly prefixed with "Bearer " in the Authorization header
- Verify the token hasn't expired (default: 1 hour)
- Ensure the secret key matches between token generation and validation

### Issue: Login Endpoint Returns 404
**Solution:** Verify the endpoint URL is `/auth/login` with POST method and correct request parameters.
Your Name - your.email@example.com

Project Link: [https://github.com/yourusername/SecuredApiJWT](https://github.com/yourusername/SecuredApiJWT)
