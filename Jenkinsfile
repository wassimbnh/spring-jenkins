pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-spring-jenkins')
        NEXUS_VERSION = "nexus3"
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
            steps {
                script {
                    // Read POM xml file using 'readMavenPom' step , this step 'readMavenPom' is included in: https://plugins.jenkins.io/pipeline-utility-steps
                    pom = readMavenPom file: "pom.xml";
                    // Find built artifact under target folder
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    // Print some info from the artifact found
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    // Extract the path from the File found
                    artifactPath = filesByGlob[0].path;
                    // Assign to a boolean response verifying If the artifact name exists
                    artifactExists = fileExists artifactPath;

                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";

                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: ARTIFACT_VERSION,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIALS,
                            artifacts: [
                                // Artifact generated such as .jar, .ear and .war files.
                                [artifactId: devopsproject,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging]
                            ]
                        );

                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
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
