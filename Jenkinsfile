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

        stage('Push Docker Image') {
    steps {
        script {
            
            def imageName = "spring-jenkins"
            def imageVersion = "1.0"
            def registryUrl = "https://hub.docker.com"  

            // Login to the container registry
            sh "docker login -u wassim158 -p Wassim1122? ${registryUrl}"

            // Tag the image for the registry
            sh "docker tag ${imageName}:${imageVersion} ${registryUrl}/${imageName}:${imageVersion}"

            // Push the image to the registry
            sh "docker push ${registryUrl}/${imageName}:${imageVersion}"
        }
    }
}



    
    }
}
