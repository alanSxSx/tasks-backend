pipeline {
    agent any
    stages {
        stage('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage('Meio') {
            steps {
                bat 'echo meio'
                bat 'echo meio 2'
                bat 'echo meio 3'
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
