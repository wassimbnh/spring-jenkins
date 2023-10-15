pipeline {
    agent any

    environment {
        // Define environment variables here, if needed
        DOCKER_IMAGE_NAME = "devops_project-spring-app "
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package' // Modify the build command for your specific project
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    def dockerImage = docker.build(env.DOCKER_IMAGE_NAME, "--file Dockerfile .")
                }
            }
        }
        }
    }
