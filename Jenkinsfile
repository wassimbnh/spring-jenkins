pipeline {
    agent any

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

        stage('Docker Compose Up') {
            steps {
                script {
                    sh 'docker-compose up -d'  
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package' 
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
