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

## 3. Getting Started

- Command
```sh
./gradlew bootRun
```

- Access
```sh
localhost:8080
```

## 4. Endpoints

```sh
curl -X GET 'http://localhost:8080?name=John'
```

```sh
curl -X GET 'http://localhost:8080/api/v1/messages'
```

```sh
curl -X POST 'http://localhost:8080/api/v1/messages' \
-H "Content-Type: application/json" \
--data '{
  "id": 1,
  "text": "dummy text"
}'

curl -X POST 'http://localhost:8080/api/v1/messages' \
-H "Content-Type: application/json" \
--data '{
  "text": "dummy text"
}'
```

```sh
curl -X GET 'http://localhost:8080/api/v1/messages/1'
```
