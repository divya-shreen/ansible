pipeline{
    agent any
    tools{
        maven 'maven'
    }
    stages{
        stage("maven clean pkg"){
            steps{
                sh 'mvn clean package -DskipTests'
            }
        }
        stage("Docker compose"){
            steps{
                 sh '''
                 con=$(docker ps -aq -f name=crud)
                 if [ $con ];then
                 docker-compose down -v
                 docker rm -f $con
                 docker system prune -af
                 else
                 echo "No Containers are Running"
                 fi
                 '''
            }
        }
        stage("Deploy"){
            steps{
                sh 'docker-compose up -d --build'
            }
        }
    }

}