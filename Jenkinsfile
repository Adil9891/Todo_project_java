pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', credentialsId: 'GitHub Credentials', url: 'https://github.com/Adil9891/Todo_project_java.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Deploy to Tomcat') {
            environment {
                TOMCAT_HOME = 'C:/Program Files/Apache Software Foundation/Tomcat 9.0'
            }
            steps {
                bat "xcopy /Y target\\todoList-0.0.1-SNAPSHOT.war \"${TOMCAT_HOME}\\webapps\""
                bat "${TOMCAT_HOME}\\bin\\shutdown.bat"
                sleep 10
                bat "${TOMCAT_HOME}\\bin\\startup.bat"
            }
        }
    }

    post {
        always {
            deleteDir()
        }

        success {
            echo 'Le pipeline s\'est terminé avec succès!'
        }

        failure {
            echo 'Le pipeline a échoué. Veuillez vérifier les logs et le code source.'
        }
    }
}
