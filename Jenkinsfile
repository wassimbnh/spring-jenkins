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

        
        stage('Docker Compose Up') {
            steps {
                script {
                    sh 'docker-compose up -d'  
                }
            }
        }

    
    }
}
