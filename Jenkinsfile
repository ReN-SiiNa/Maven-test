pipeline {
    agent any
    tools {
        maven 'sonarmaven' // Ensure this matches the Maven configuration in Jenkins
    }
    environment {
        SONAR_TOKEN = credentials('sonar-token') // SonarQube token stored in Jenkins credentials
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17' // Path to JDK
        PATH = "${JAVA_HOME}\\bin;%PATH%" // Append JDK to PATH
    }

    stages {
        stage('Checkout') {
            steps {
                // Check out code from SCM
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Starting SonarQube Analysis...'
                withSonarQubeEnv('sonarqube') { // Ensure 'sonarqube' matches your SonarQube configuration in Jenkins
                    bat """
                        mvn sonar:sonar ^
                        -Dsonar.projectKey=aryanMaven ^
                        -Dsonar.sources=src/main/java ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.login=%SONAR_TOKEN%
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs for errors.'
        }
    }
}
