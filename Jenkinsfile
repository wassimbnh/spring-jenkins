pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Étape de récupération du code source depuis le référentiel Git
                checkout scm
            }
        }

        stage('Display System Date') {
            steps {
                // Étape d'affichage de la date système
                script {
                    def currentDate = sh(script: 'date', returnStdout: true).trim()
                    echo "La date système est : ${currentDate}"
                }
            }
        }
    }
}
