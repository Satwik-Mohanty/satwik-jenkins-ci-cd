pipeline {
    agent any
    tools {
        maven 'Maven 3.9.9'  // Ensure Maven is configured in Jenkins Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
        script {
            checkout([
                $class: 'GitSCM',
                branches: [[name: '*/main']],  // Ensure it's set to the correct branch
                userRemoteConfigs: [[url: 'https://github.com/Satwik-Mohanty/satwik-jenkins-ci-cd']]
            ])
        }
    }
        }

        stage('Get Branch Name') {
    steps {
        script {
            env.ACTUAL_BRANCH = sh(script: "git rev-parse --abbrev-ref main", returnStdout: true).trim()
            echo "🟢 Detected Branch: ${env.ACTUAL_BRANCH}"
        }
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
            steps {
                script {
                    if (env.BRANCH_NAME == 'main') {
                        echo '✅ Deploying to Staging...'
                        sh 'cp target/*.jar /tmp/staging-app.jar'
                    } else {
                        echo "🔍 Detected Branch: ${env.BRANCH_NAME}"
                        echo 'Skipping Staging Deployment (Not on main branch)'
                    }
                }
            }
        }

        stage('Deploy to Production') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'main') {
                        echo '✅ Deploying to Production...'
                        sh 'cp target/*.jar /tmp/production-app.jar'
                    } else {
                        echo "🔍 Detected Branch: ${env.BRANCH_NAME}"
                        echo 'Skipping Production Deployment (Not on main branch)'
                    }
                }
            }
        }
    }

    post {
        always {
            echo '📢 Pipeline execution completed!'
        }
        success {
            echo '✅ Build and deployment successful!'
        }
        failure {
            echo '❌ Pipeline failed! Notifying team...'
        }
    }
}
