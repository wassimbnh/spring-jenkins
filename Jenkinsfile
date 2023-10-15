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
            // Define variables for the Docker image name and version
            def imageName = "spring-jenkins"
            def imageVersion = "1.0"

            // Build the Docker image
            sh "docker build -t ${imageName}:${imageVersion} ."
        }
    }
}


    
    }
}
