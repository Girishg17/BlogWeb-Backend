pipeline {
    agent any

    environment {
        // Define the Docker image name and version
        IMAGE_NAME = "pes1ug20cs552/blogweb"
        IMAGE_VERSION = "v1.0.${BUILD_NUMBER}"
    }

    stages {
        stage('Build') {
            steps {
                // Build the Maven project
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Build the Docker image from the Dockerfile
                script {
                    docker.build("${IMAGE_NAME}:${IMAGE_VERSION}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                // Log in to Docker registry (e.g., Docker Hub)
                withCredentials([usernamePassword(credentialsId: 'dckr_pat_RHQv4dZb0CeRpJs8SPpJd358E6c', usernameVariable: 'pes1ug20cs552', passwordVariable: 'Girish#123')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    // Push the Docker image
                    sh "docker push ${IMAGE_NAME}:${IMAGE_VERSION}"
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                // Deploy to Kubernetes using kubectl
                withKubeConfig([credentialsId: 'dckr_pat_RHQv4dZb0CeRpJs8SPpJd358E6c']) {
                    sh """
                    kubectl set image deployment/blogweb blogweb=${IMAGE_NAME}:${IMAGE_VERSION} --record
                    kubectl rollout status deployment/blogweb
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment completed successfully!'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}
