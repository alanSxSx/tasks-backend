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
                bat 'mvn clean test jacoco:report'
            }
        }

        stage('Sonar Analysis') {
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    bat "${scannerHome}\\bin\\sonar-scanner -e " +
                        "-Dsonar.projectKey=DeployBack " +
                        "-Dsonar.host.url=http://localhost:9000 " +
                        "-Dsonar.login=sqp_5d2cbcc4ff612aca3c14ac32bfe98f0668ab342d " +
                        "-Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml " +
                        "-Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**/Application.java " +
                        "-Dsonar.java.binaries=target"
                }
            }
        }

        stage('Quality Gate') {
            steps {
                sleep(10)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

				stage('Deploy Backend') {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'tomCatLogin', path: '', url: 'http://localhost:8080/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }

				stage('API Test') {
            steps {
								dir('api-test') {
                git credentialsId: 'gitHub', url: 'https://github.com/alanSxSx/tasks-api-test'
								bat 'mvn test'
								}
            }
        }

				stage('Deploy Frontend') {
            steps {
								dir('frontend') {
                git credentialsId: 'gitHub', url: 'https://github.com/alanSxSx/tasks-frontend'
								bat 'mvn clean package'
								deploy adapters: [tomcat9(credentialsId: 'tomCatLogin', path: '', url: 'http://localhost:8080/')], contextPath: 'tasks', war: 'target/tasks.war'
								}
            }
        }

				stage('Functional Test') {
            steps {
								dir('functional-test') {
                git credentialsId: 'gitHub', url: 'https://github.com/alanSxSx/tasks-functional-test'
								bat 'mvn test'
								}
            }
        }

				stage('Deploy Prod') {
            steps {
								bat 'docker-compose build'
                bat 'docker-compose up -d'
            }
        }
    }
}
