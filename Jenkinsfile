pipeline {
    agent any
    tools {
        maven 'sonarmaven' // Maven installation configured in Jenkins
    }
    environment {
        SONAR_TOKEN = credentials('sonar-token') // Securely retrieve SonarQube token
        SONAR_HOST_URL = 'http://localhost:9000' // Replace with your SonarQube server URL
        SONAR_PROJECT_KEY = 'mavenaryan' // Replace with your project key
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
                sh 'mvn -B -DskipTests clean package' // Skipping tests during build
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test' // Running tests after build
            }
            post {
                always {
                    echo 'Publishing test results...'
                    junit 'target//surefire-reports//*.xml' // Publishing JUnit test results
                }
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
                    -Dsonar.login=${SONAR_TOKEN}
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
