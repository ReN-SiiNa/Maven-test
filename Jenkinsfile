pipeline {
    agent any
    tools {
        maven 'sonarmaven' // Ensure this matches the Maven configuration in Jenkins
    }
    environment {
        SONAR_TOKEN = credentials('sonar-token') // SonarQube token stored in Jenkins credentials
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out the code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean package'
            }
        }

        stage('Code Coverage') {
            steps {
                echo 'Running tests and generating JaCoCo coverage report...'
                bat 'mvn clean verify' // Runs tests and generates JaCoCo reports
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Starting SonarQube Analysis...'
                bat """
                    mvn sonar:sonar ^ 
                    -Dsonar.projectKey=mavenaryan ^ 
                    -Dsonar.sources=src/test/java ^ 
                    -Dsonar.host.url=http://localhost:9000 ^ 
                    -Dsonar.login=%SONAR_TOKEN% ^ 
                    -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                """
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
