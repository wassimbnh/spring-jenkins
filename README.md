# Spring Boot CI/CD Project with Jenkins, SonarQube, Docker, Docker Compose, Grafana, and Prometheus
## Overview
This repository contains a Spring Boot project set up for Continuous Integration and Continuous Deployment (CI/CD) using Jenkins, SonarQube for code quality analysis, Docker for containerization, and Docker Compose for managing the application stack. Additionally, Grafana and Prometheus are integrated for monitoring and visualization of application metrics.

## Prerequisites
Make sure you have the following tools installed on your system:

Java Development Kit (JDK)
Docker
Docker Compose
Jenkins
SonarQube
Grafana
Prometheus

## CI/CD Workflow
Jenkins pipeline is triggered on code changes.
Code is built, tested, and analyzed by SonarQube for code quality.
Docker image is built and pushed to a Docker registry.
Docker Compose is used to deploy the application stack.
Prometheus collects and stores metrics from the application.
Grafana visualizes metrics using the configured dashboard.

## Running the Application
```bash
docker-compose up -d
```
## Monitoring
Access Grafana at http://localhost:3000 and Prometheus at http://localhost:9090.

## Contributing
Feel free to contribute to the project by opening issues or creating pull requests.
