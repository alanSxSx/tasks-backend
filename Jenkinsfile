pipeline {
    agent any

    environment {
        scannerHome = tool 'SONAR_SCANNER'
    }

    stages {
        stage('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }

        stage('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Sonar Analysis') {
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    bat "${scannerHome}/bin/sonar-scanner -e " +
                        "-Dsonar.projectKey=DeployBack " +
                        "-Dsonar.host.url=http://localhost:9000 " +
                        "-Dsonar.projectBaseDir=C:\\\\Users\\\\Alan\\\\.jenkins\\\\workspace\\\\DeployBack " +
                        "-Dsonar.login=sqp_5d2cbcc4ff612aca3c14ac32bfe98f0668ab342d " +
                        "-Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml " +
                        "-Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**/Application.java " +
                        "-Dsonar.java.binaries=target"
                }
            }
        }
    }
}
