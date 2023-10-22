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

        /*stage('SonarQube Analysis') {
            steps {
                
                    withSonarQubeEnv('sonarqube') {
                        sh "mvn sonar:sonar"
                    }
                }
        }*/
        

        stage('Build Docker Image') {
            steps {
                script {
                    def imageName = "devops-project"
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

        /*stage('Push Image') {
            steps {
                script {
                    def imageName = "devops-project"
                    def imageVersion = "1.0"
                    sh "docker tag ${imageName}:${imageVersion} $DOCKERHUB_CREDENTIALS_USR/${imageName}:${imageVersion}"
                    sh "docker push $DOCKERHUB_CREDENTIALS_USR/${imageName}:${imageVersion}"
                }
            }
        }*/

    stage('Deploy to Nexus') {
         steps {
           script {
            // Set up Maven settings
            def mavenSettings = readFile("${WORKSPACE}/settings.xml")
            
            // Deploy Maven artifacts to Nexus
            sh "mvn deploy -s ${mavenSettings}"
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
