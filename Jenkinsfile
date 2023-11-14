pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-spring-jenkins')
         NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.33.10:8081"
        NEXUS_REPOSITORY = "devopsproject"
        NEXUS_CREDENTIAL_ID = "nexusCredential"
    }

    stages {
        

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

       stage('SonarQube Analysis') {
            steps {
                
                    withSonarQubeEnv('sonarqube') {
                        sh "mvn sonar:sonar"
                    }
                }
        }

        stage('Junit/Mockito') {
            steps {
                sh 'mvn test'
            }
        }
        

        stage("Publish to Nexus") {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }
        
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

        stage('Deploy to DockerHub') {
            steps {
                script {
                    def imageName = "devops-project"
                    def imageVersion = "1.0"
                    sh "docker tag ${imageName}:${imageVersion} $DOCKERHUB_CREDENTIALS_USR/${imageName}:${imageVersion}"
                    sh "docker push $DOCKERHUB_CREDENTIALS_USR/${imageName}:${imageVersion}"
                }
            }
        }

        stage("Docker Compose") {
            steps {
                sh 'docker-compose up --build -d'
            }
        }

          
    }
    

    post {
        always {
            sh 'docker logout'
        }
    }
}
