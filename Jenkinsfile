pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
    }

    environment {
        BRANCH_NAME = "${env.BRANCH_NAME}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: "*/${env.BRANCH_NAME}"]],
                    userRemoteConfigs: [[url: 'https://github.com/Satwik-Mohanty/satwik-jenkins-ci-cd']]
                ])
            }
        }

        stage('Build') {
            agent { label 'build-agent' } // Executes on build agent node
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            agent { label 'test-agent' } // Executes on test agent node
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            parallel {
                stage('Deploy to Staging') {
                    agent { label 'staging-agent' } // Executes on staging node
                    when {
                        expression { env.BRANCH_NAME == 'main' }
                    }
                    steps {
                        echo "Deploying to Staging (Branch: ${env.BRANCH_NAME})"
                        sh 'cp target/*.jar /tmp/staging-app.jar'
                    }
                }

                stage('Deploy to Production') {
                    agent { label 'prod-agent' } // Executes on production node
                    when {
                        expression { env.BRANCH_NAME == 'main' }
                    }
                    steps {
                        echo "Deploying to Production (Branch: ${env.BRANCH_NAME})"
                        sh 'cp target/*.jar /tmp/production-app.jar'
                    }
                }
            }
        }

        stage('Check JAR File') {
            steps {
                script {
                    def fileExistsProd = sh(script: "[ -f /tmp/production-app.jar ] && echo 'FOUND' || echo 'NOT FOUND'", returnStdout: true).trim()
                    def fileExistsStaging = sh(script: "[ -f /tmp/staging-app.jar ] && echo 'FOUND' || echo 'NOT FOUND'", returnStdout: true).trim()
                    echo " JAR File in Production: ${fileExistsProd}"
                    echo " JAR File in Staging: ${fileExistsStaging}"
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed!'
        }
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Pipeline failed! Check logs for more details.'
        }
    }
}
