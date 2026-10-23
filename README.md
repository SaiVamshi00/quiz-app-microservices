# Quiz App – Microservices

Spring Boot microservices version of the quiz app.

| Module | Description |
|---|---|
| service-registry | Eureka service registry |
| api-gateway | Spring Cloud Gateway, single entry point |
| question-service | Manages questions (PostgreSQL `questiondb`) |
| quiz-service | Manages quizzes, calls question-service via OpenFeign (PostgreSQL `quizdb`) |

Start order: service-registry, question-service, quiz-service, api-gateway.
