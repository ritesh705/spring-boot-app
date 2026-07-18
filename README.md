# spring-boot-app

This is spring boot app with multiple APIs

## Swagger API Documentation

Once the application is running, access the Swagger UI at:
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## Spring Boot Actuator Endpoints

This application includes Spring Boot Actuator for monitoring and management. Once the application is running, the following actuator endpoints are available:

- **Health**: http://localhost:8080/actuator/health - Application health status
- **Info**: http://localhost:8080/actuator/info - Application information
- **Metrics**: http://localhost:8080/actuator/metrics - Application metrics
- **Env**: http://localhost:8080/actuator/env - Environment properties
- **Loggers**: http://localhost:8080/actuator/loggers - Logger configuration
- **Threaddump**: http://localhost:8080/actuator/threaddump - Thread dump
- **Heapdump**: http://localhost:8080/actuator/heapdump - Heap dump
- **Mappings**: http://localhost:8080/actuator/mappings - Request mappings
- **Beans**: http://localhost:8080/actuator/beans - Spring beans information
- **Conditions**: http://localhost:8080/actuator/conditions - Condition evaluation report

Note: Some endpoints may require additional configuration in `application.yml` to be enabled.