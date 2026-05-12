pipeline{
    agent any
    tools{
        maven 'maven'
    }

    environment {
        APP_NAME = "divya2057/springboot-app"
        DB_IMAGE = "divya2057/springboot-db"
        TAG = "latest"
        CONTAINER_NAME = "spring-container"
    }

    parameters {
        choice(
            name: 'ACTION',
            choices: ['deploy', 'remove', 'update'],
            description: 'Choose action'
        )
    }

    stages {

        stage('Pull Code') {
            steps {
                git branch: 'main', url: 'https://github.com/divya-shreen/ansible.git'
            }
            post {

               success {
                   echo 'Pipeline Executed Successfully'
                }

                failure {
                   echo 'Pipeline Failed'
                }

                always {
                    cleanWs()
                }
            }
        }

        stage('Check Workspace') {
           steps {
                sh 'find . -name pom.xml'
            }
        }
    
        stage("Build maven pkg"){
            steps{
                sh 'ls -la'
                sh 'mvn clean package'
            }
        }

        stage('Build App Image') {
            steps {
                sh 'docker build -t divya2057/springboot-app:latest .'
            }
        }

        stage('Build DB Image') {
            steps {
                sh 'docker build -t divya2057/springboot-db:latest .'
            }
        }

        stage('Tag Image') {
            steps {
                sh 'docker tag divya2057/static-web:latest divya2057/static-web:v1.1'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    sh 'echo $PASS | docker login -u $USER --password-stdin'
                }
            }
        }

        stage('Push Image') {
            steps {
                sh 'docker push divya2057/springboot-app:latest'
                sh 'docker push divya2057/springboot-db:latest'

            }
        }

        stage('Deploy Using Docker Compose') {
            when {
                expression { params.ACTION == 'deploy' }
            }

            steps {
                sh 'docker compose down || true'
                sh 'docker compose up -d'
            }
        }

        stage('Remove Containers') {
            when {
                expression { params.ACTION == 'remove' }
            }

            steps {
                sh 'docker compose down'
            }
        }

        stage('Update Application') {
            when {
                expression { params.ACTION == 'update' }
            }

            steps {
                sh 'docker compose pull'
                sh 'docker compose up -d --build'
            }
        }

        stage('Check Running Containers') {
            steps {
                sh 'docker ps'
            }
        }

        stage('Clean Unused Images') {
            steps {
                sh 'docker image prune -f'
            }

            post {

              success {
                echo 'Pipeline Executed Successfully'
              }

               failure {
                  echo 'Pipeline Failed'
                }

                always {
                  cleanWs()
                }
            }
        }
    }    
}
