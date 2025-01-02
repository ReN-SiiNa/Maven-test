pipeline {
    agent any
    tools {
        maven 'sonarmaven'
    }
    environment {
        SONAR_TOKEN = credentials('sonar-token')
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
                bat 'mvn clean verify'
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
