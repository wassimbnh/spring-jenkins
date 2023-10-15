pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-spring-jenkins')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def imageName = "spring-jenkins"
                    def imageVersion = "1.0"

                    // Build the Docker image
                    sh "docker build -t ${imageName}:${imageVersion} ."
                }
            }
        }

        stage('Login Dockerhub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push Image') {
            steps {
                script {
                    def imageName = "spring-jenkins"
                    def imageVersion = "1.0"
                    
                    // Push the Docker image to Docker Hub
                    sh "docker push ${imageName}:${imageVersion}"
                }
            }
        }

        post {
            always{
                sh 'docker logout
            }
        }
    }
}
