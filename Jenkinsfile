pipeline {
    agent any
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
        stage('Fim') {
            steps {
                sleep(5)
                bat 'echo FIM'
            }
        }
    }
}
