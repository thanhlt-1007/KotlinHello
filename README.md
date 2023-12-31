# KOTLIN HELLO

## 1. Development Environment

- Spring Boot 3.1.2

- Gradle 8.2.1

- Java 17.0.7

- Kotlin 1.7.21

## 2. Spring Boot Dependencies

## a. Web

- Spring Web

## b. SQL

- Spring Data JDBC

- H2 Database

## c. Dotenv

- Dotenv Kotlin 6.4.1

## 3. Getting Started

- Command
```sh
./gradlew bootRun
```

- Access
```sh
http://localhost:8080
```

## 4. Endpoints

### a. Web

```sh
curl -X GET 'http://localhost:8080?name=John'
```

```sh
curl -X GET 'http://localhost:8080/envs'
```

### b. API

```sh
curl -X GET 'http://localhost:8080/api/v1/messages'
```

```sh
curl -X POST 'http://localhost:8080/api/v1/messages' \
-H "Content-Type: application/json" \
--data '{
  "text": "dummy text"
}'
```

```sh
curl -X POST 'http://localhost:8080/api/v1/messages' \
-H "Content-Type: application/json" \
--data '{
  "id": "424a6d52-1c34-4c7d-8c61-680b8c37e72e",
  "text": "upda6te dummy text"
}'
```

```sh
curl -X GET 'http://localhost:8080/api/v1/messages/424a6d52-1c34-4c7d-8c61-680b8c37e72e'
```
