# Jenkins Pipeline Practice for Maven-Based Projects

This repository is designed for practicing Jenkins pipeline configurations and integrations for Maven-based projects. It provides a simple Maven project that can be used to test different Jenkins pipeline stages, including build, test, and SonarQube analysis.

## Table of Contents

- [Project Setup](#project-setup)
- [Jenkins Pipeline Overview](#jenkins-pipeline-overview)
- [Pipeline Stages](#pipeline-stages)
- [SonarQube Integration](#sonarqube-integration)
- [How to Use](#how-to-use)


---

## Project Setup

This repository contains a basic Maven project structure that includes:

- `src/main/java`: Contains the Java source files.
- `src/test/java`: Contains the test files for unit testing.
- `pom.xml`: The Maven Project Object Model file for managing dependencies and build configurations.
- `target/`: Directory where the compiled classes and packaged JAR file will be stored after the build.

## Jenkins Pipeline Overview

The Jenkins pipeline defined in this repository is intended to automate the build and deployment process for Maven projects. The pipeline is written in **Declarative Pipeline** syntax for simplicity and readability. It includes the following stages:

1. **Checkout**: Retrieves the source code from the Git repository.
2. **Build**: Runs the `mvn clean package` command to compile and package the project into a JAR file.
3. **SonarQube Analysis**: Analyzes the project using SonarQube for code quality checks and generates reports.

## Pipeline Stages

### Checkout
This stage checks out the latest code from the configured source code repository (e.g., GitHub or GitLab).

```groovy
checkout scm
```

### Build
The build stage runs the Maven command `mvn clean package` to compile and package the project. If successful, a JAR file is generated in the `target/` directory.

```groovy
bat 'mvn clean package'
```

### SonarQube Analysis
This stage performs code quality analysis using SonarQube. The project is analyzed using the `mvn sonar:sonar` command, and the results are sent to a SonarQube server for further review.

```groovy
withSonarQubeEnv('sonarqube') {
    bat "mvn sonar:sonar -Dsonar.projectKey=dummy-maven-project -Dsonar.sources=src/main/java -Dsonar.host.url=http://localhost:9000 -Dsonar.login=%SONAR_TOKEN%"
}
```

## SonarQube Integration

To integrate SonarQube analysis with Jenkins, you need to have the **SonarQube Scanner for Maven** configured. Make sure that the following are set up in Jenkins:

1. **SonarQube Server**: The SonarQube server URL and credentials must be configured in Jenkins.
2. **SonarQube Token**: Create a token in SonarQube and store it in Jenkins credentials.
3. **Maven**: Jenkins should have Maven installed, and the correct version should be configured.

## How to Use

1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/ReN-SiiNa/Maven-test.git
   ```

2. Create a **Jenkinsfile** in the root directory of this project (already included in the repository).

3. Set up a Jenkins job to pull this repository and run the pipeline defined in the Jenkinsfile.

4. Configure the required tools in Jenkins:
   - **Maven**: Ensure Maven is installed on Jenkins and set in the Jenkins global tools configuration.
   - **SonarQube**: Make sure SonarQube is set up in Jenkins and the SonarQube plugin is installed.

5. Run the Jenkins pipeline. It will automatically execute the following:
   - Checkout the latest code from the repository.
   - Build the project using Maven.
   - Perform SonarQube analysis and generate reports.



This readme content provides an overview of setting up a simple Maven-based project for Jenkins pipeline practice and integrating it with SonarQube for code quality analysis.
