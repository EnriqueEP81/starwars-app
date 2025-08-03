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

### Run with Kubernetes
You can also deploy the application on a local Kubernetes cluster using Minikube.
Two resource files are included:
- ```deployment.yml``` (deploys 3 replicas of the app)
- ```service.yml``` (exposes the app on a NodePort)
To run it on Minikube, follow these steps:
```bash
# Build the Docker image inside Minikube
minikube image build -t starwars-app:latest .

# Deploy the application
kubectl apply -f deployment.yml
kubectl apply -f service.yml

# Check pods and services status
kubectl get pods
kubectl get services

# Get Minikube IP
minikube ip

# Access the app at: http://<minikube_ip>:<nodePort>

- ```