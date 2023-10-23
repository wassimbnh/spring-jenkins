pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-spring-jenkins')
        NEXUS_VERSION = "nexus3"
        NEXUS_USER = "admin"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "http://192.168.33.10:8081"
        NEXUS_REPOSITORY = "devopsproject"
        NEXUS_CREDENTIALS = 'nexusCredential'
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
	@@ -49,7 +50,7 @@ pipeline {
            }
        }*/

         /*stage('Push Image') {
            steps {
                script {
                    def imageName = "devops-project"
	@@ -58,7 +59,7 @@ pipeline {
                    sh "docker push $DOCKERHUB_CREDENTIALS_USR/${imageName}:${imageVersion}"
                }
            }
        }*/

         stage("Publish to Nexus") {
            steps {
                script {
                    def version = env.ARTIFACT_VERSION
                    nexusArtifactUploader(
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        nexusUrl: env.NEXUS_URL,
                        groupId: 'tn.esprit',
                        version: version,
                        repository: env.NEXUS_REPOSITORY,
                        credentialsId: env.NEXUS_CREDENTIALS,
                        artifacts: [
                            [
                                artifactId: 'spring-jenkins',
                                classifier: '',
                                file: "target/my-service-${version}.jar",
                                type: 'jar'
                            ]
                        ]
                    )
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
