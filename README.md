# SimpleHttpAPI

## Run application
mvn spring-boot:run

## Run tests
mvn test

## Endpoint
GET /hello-world?name=alice

## Behavior
- A–M → 200 OK
- N–Z → 400 Bad Request
- Missing/empty → 400 Bad Request

## Assumptions
- Case-insensitive alphabet check
- Trims whitespace