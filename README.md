# Star Wars Viewer

A simple Spring Boot project to display data from the [Star Wars API (SWAPI)](https://swapi.py4e.com/).  
Currently, it shows **People** and **Starships** in a basic web interface.

## Tech Stack
- Java 17+
- Spring Boot
- Gradle
- Thymeleaf
- Docker & Docker Compose

## Getting Started

### Run the project
```bash
./gradlew bootRun
```

### Run with Docker

Build and start the container:

```bash
docker-compose up --build
```
The app will be available at http://localhost:6969.