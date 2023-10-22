pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-spring-jenkins')
        NEXUS_VERSION = "nexus3"
        NEXUS_USER = "admin"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "http://192.168.33.10:8081"
        NEXUS_REPOSITORY = "devopsproject"
        NEXUS_CREDENTIALS = "nexusCredential"
        ARTIFACT_VERSION = "${BUILD_NUMBER}"
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
        

        /*stage('Build Docker Image') {
            steps {
                script {
                    def imageName = "devops-project"
                    def imageVersion = "1.0"
                    sh "docker build -t ${imageName}:${imageVersion} ."
                }
            }
        }*/

       /* stage('Login Dockerhub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }*/

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

    stage("Publish to Nexus") {
    step{
        nexusArtifactUploader(
        nexusVersion: 'nexus3',
        protocol: 'http',
        nexusUrl: "${NEXUS_URL}",
        groupId: 'tn.esprit',
        version: version,
        repository: "${NEXUS_REPOSITORY}",
        credentialsId: "${NEXUS_CREDENTIALS}",
        artifacts: [
            [artifactId: 'spring-jenkins',
             classifier: '',
             file: 'my-service-' + version + '.jar',
             type: 'jar']
        ]
     )
                
                
                }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}
