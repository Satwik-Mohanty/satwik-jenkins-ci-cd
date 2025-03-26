pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Satwik-Mohanty/satwik-jenkins-ci-cd'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy to Staging') {
            when { branch 'develop' }
            steps {
                echo 'Deploying to Staging...'
            }
        }

        stage('Deploy to Production') {
            when { branch 'main' }
            steps {
                echo 'Deploying to Production...'
            }
        }
    }

    post {
        failure {
            echo 'Pipeline failed! Notifying team...'
        }
    }
}
