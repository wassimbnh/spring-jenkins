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

        stage('SonarQube Analysis') {
    steps {
        script {
            def scannerHome = tool name: 'SonarQubeScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
            withSonarQubeEnv('spring-jenkins') {
                sh "./mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.9.2155:sonar"
            }
        }
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
                    sh "docker tag ${imageName}:${imageVersion} $DOCKERHUB_CREDENTIALS_USR/${imageName}:${imageVersion}"
                    sh "docker push $DOCKERHUB_CREDENTIALS_USR/${imageName}:${imageVersion}"
                }
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}
