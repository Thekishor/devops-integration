# DevOps Integration Microservice

## Overview
This project is a Spring Boot microservice named "devops-integration" that demonstrates:
- Full CRUD (Create, Read, Update, Delete) REST API operations
- Input validation
- Global exception handling
- GitHub integration
- Jenkins CI/CD pipeline
- Docker image creation
- Docker-compose.yaml configuration

---

## Features
- REST APIs: Supports `GET`, `GET ALL`, `POST`, `PUT`, `DELETE` operations.
- Input Validation: Uses `@Valid` annotations to validate request payloads.
- Global Exception Handling: Centralized error handling with `@ControllerAdvice`.
- GitHub Integration: Project is hosted on GitHub.
- Jenkins Pipeline: Automates build, test, and deployment processes.
- Docker Support: Docker image creation and deployment.
- Docker Compose: Configuration for running multiple services.

---

## API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/user/{userId}` | Fetch a record by ID |
| GET | `/user` | Fetch all records |
| POST | `/user` | Create a new record |
| PUT | `/user/{userId}` | Update an existing record |
| DELETE | `/user/{userId}` | Delete a record |

### Example Request (POST)
```json
{
    "username": "Kishor",
    "email": "Kishorpandey633@gmail.com",
    "password": "Kishor@22",
    "address": {
        "state": "Lumbini",
        "city": "Butwal",
        "zipcode": "YE376"
    }
}
```

### Example Response
```json
{
    "userId": "67b56de01ce51a216c9122aa",
    "username": "Kishor",
    "email": "Kishorpandey633@gmail.com",
    "password": "Kishor@22",
    "address": {
        "state": "Lumbini",
        "city": "Butwal",
        "zipcode": "YE376"
    }
}
```

---

## Global Exception Handling
Handled using `@ControllerAdvice`:
- `MethodArgumentNotValidException` for validation errors.
- `ResourceNotFoundException` for non-existent records.
- `Exception` for generic errors.

---

## GitHub Integration
1. Initialize Git repository:
   ```sh
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/Thekishor/devops-integration.git
   git push -u origin main
   ```

---

## Jenkins CI/CD Pipeline
### Jenkinsfile
```groovy
pipeline{
    agent any
    stages{
        stage('Checkout Code'){
            steps{
                git(
                    url: 'https://github.com/Thekishor/devops-integration.git',
                    branch: 'main',
                    credentialsId: 'git_hub_token'
                )
            }
        }
        
        stage('Build Maven'){
            steps{
                bat 'mvn clean verify'
            }
        }
        
        stage('Build Docker Image'){
            steps{
                script{
                    bat 'docker build -t thekishor/devops-integration:0.0.1 .'
                }
            }
        }
        
        stage('Push image to Hub'){
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'docker_hub_token', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASSWORD')]){
                        bat "docker login -u %DOCKER_USER% -p %DOCKER_PASSWORD%"
                        bat "docker push %DOCKER_USER%/devops-integration:0.0.1"
                    }
                }
            }
        }
    }
}
```

---

## Docker Integration
### Dockerfile
```dockerfile
FROM openjdk:21-jdk-slim
EXPOSE 9099
ADD target/devops-integration.jar devops-integration.jar
ENTRYPOINT ["java","-jar","/devops-integration.jar"]
```

## Docker Compose
### `docker-compose.yaml`
```yaml
services:
  devops-integration:
    image: your-dockerhub-username/devops-integration:latest
    container_name: devops-integration
    ports:
      - "8080:8080"
    restart: always
```

### Run with Docker Compose
docker-compose up -d

---

## Conclusion
This microservice integrates DevOps principles with:
- REST API development
- CI/CD using Jenkins
- Containerization with Docker
- Automated service deployment via Docker Compose
