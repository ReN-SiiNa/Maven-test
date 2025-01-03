pipeline {
    agent any

    tools {
        maven 'sonarmaven' // Maven installation configured in Jenkins
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token') // Securely retrieve SonarQube token
        SONAR_HOST_URL = 'http://localhost:9000' // Replace with your SonarQube server URL
        SONAR_PROJECT_KEY = 'mavenaryan'         // Replace with your project key
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
                sh 'mvn clean package'
            }
        }

        stage('Code Coverage') {
            steps {
                echo 'Running tests and generating JaCoCo coverage report...'
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Starting SonarQube Analysis...'
                sh """
                    mvn sonar:sonar \
                    -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                    -Dsonar.sources=src/main/java \
                    -Dsonar.tests=src/test/java \
                    -Dsonar.host.url=${SONAR_HOST_URL} \
                    -Dsonar.login=${SONAR_TOKEN} \
                    -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                """
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
            // Publish JaCoCo Code Coverage Report
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: false,
                reportDir: 'target/site/jacoco',
                reportFiles: 'index.html',
                reportName: 'JaCoCo Code Coverage Report'
            ])
        }
        failure {
            echo 'Pipeline failed. Please check the logs for errors.'
        }
    }
}
