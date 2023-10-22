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

        stage("Publish to Nexus") {
            steps {
                script {
                    def version = ARTIFACT_VERSION
                    nexusArtifactUploader(
                        nexusVersion: NEXUS_VERSION,
                        protocol: NEXUS_PROTOCOL,
                        nexusUrl: NEXUS_URL,
                        groupId: 'tn.esprit',
                        version: version,
                        repository: NEXUS_REPOSITORY,
                        credentialsId: NEXUS_CREDENTIALS,
                        artifacts: [
                            [artifactId: 'spring-jenkins',
                             classifier: '',
                             file: "target/my-service-${version}.jar",
                             type: 'jar']
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
