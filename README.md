# Micro Components Test

A simple microservices project built with Spring Boot and Spring Cloud.

## How to Run the Project

### 1. Navigate to a server folder

Open a terminal inside the microservice/server folder you want to run.

### 2. Run the service

**Windows:**

```bash
mvnw.cmd spring-boot:run
```

**macOS/Linux:**

```bash
./mvnw spring-boot:run
```

### 3. Start the services in the following order

1. Config Server
2. Discovery Server
3. API Gateway
4. Book Service
5. Rating Service

## Test APIs

Once all services are running, you can test the APIs through the API Gateway.

### Get Books

```text
http://localhost:8080/book-service/books
```

### Search Ratings by Book ID

```text
http://localhost:8080/rating-service/rating/search?bookId=2
```
