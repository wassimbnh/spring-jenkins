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
}
