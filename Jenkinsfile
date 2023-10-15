pipeline {
    agent any

        environment {
             DOCKERHUB_USERNAME = "wassim158"
             DOCKERHUB_CREDENTIALS_PSW = "Wassim1122?"
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
              withCredentials([usernamePassword(credentialsId: 'dckr_pat_TutLDlHsMsYOkaa-u9AS9-OcvLQ', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_CREDENTIALS_PSW')]) {
              sh 'docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_CREDENTIALS_PSW'
      }
   }
}

    }
}
