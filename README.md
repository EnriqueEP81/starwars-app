# Star Wars Viewer

A simple Spring Boot project to display data from the [Star Wars API (SWAPI)](https://swapi.py4e.com/).  
Currently, it shows **People** and **Starships** in a basic web interface.

## Tech Stack
- Java 17+
- Spring Boot
- Gradle
- Thymeleaf
- Docker & Docker Compose

## Implementation Details
The application demonstrates two different approaches for fetching and displaying data:

- **People Page**  
  Uses a simple pagination approach, fetching data **page by page** directly from SWAPI.  
  Sorting is applied only to the currently displayed page.

- **Starships Page**  
  Implements a more advanced logic:
    - When the user first loads the page or performs a search, **all starships are fetched** from SWAPI and stored in an **in-memory cache**.
    - Sorting is applied globally to the entire dataset.
    - Pagination then works on the cached dataset, avoiding repeated calls to SWAPI.
    - This makes the navigation between pages much faster after the first load.

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
 ```