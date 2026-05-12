pipeline{
    agent any
    tools{
        maven 'maven'
    }

    parameters {
        string(name: "UPDATE", defaultValue: "update.yml")
        string(name: "REMOVE_DB", defaultValue: "remove_db.yml")
        string(name: "REMOVE_APP", defaultValue: "remove_app.yml")
        string(name: "DEPLOY_DB", defaultValue: "deploydb.yml")
        string(name: "DEPLOY_APP", defaultValue: "deploy_app.yml")  
    }

    stages{
        stage("Build"){
            steps{
                sh '''
                mvn clean package -DskipTests
                '''
            }
            post{
                success{
                    echo "Build Stage Completed"
                }
                failure{
                    echo "Failure to Build"
                }
            }
        }
        stage("Docker build and Run"){
            steps{
                sh '''
                docker compose up -d --build
                '''
            }
            post{
                success{
                    echo "Docker Build Stage Completed"
                }
                failure{
                    echo "Failure to Docker Build and Run"
                }
            }  
        }
        stage("Docker tag and Push into DockerHub"){
            steps{
            sh '''
                docker commit crud divya 
                docker commit 
                docker tag divya divya2057/springboot-app
                docker tag navya divya2057/springboot-db
                sleep 1
                docker login -u divya2057 -p Dv__yaa@2057
                docker push divya2057/springboot-app
                docker push divya2057/springboot-db
            '''
            }
        
        post{
                success{
                    echo "Snapshot successfully Taken"
                }
                failure{
                    echo "Failed To Take a snapshot"
                }
            }
        }
            stage("Remove img in Master Node"){
                steps{
                sh '''
                docker compose down -v
                docker system prune -af
                '''
                }
                post{
                success{
                    echo "Docker img successfully Removed"
                }
                failure{
                    echo "Failed To Remove docker img"
                }
            }
            }
            stage("Update And Install"){
                steps{
                sshagent(['kkk-kkk']){
                sh """
                ansible-playbook ${params.UPDATE}
                """
                }
                }
                post{
                success{
                    echo "Successfully Installed"
                }
                failure{
                    echo "Failed To Install"
                }
            }
            }
            stage("Remove DB Container"){
                steps{
                    sshagent(['kkk-kkk']){
                sh """
                    ansible-playbook ${params.REMOVE_DB}
                """
                }
                }
                post{
                success{
                    echo " successfully Removed DB "
                }
                failure{
                    echo "Failed To Remove DB"
                }
            }
            }
            stage("Remove APP Container"){
                steps{
                    sshagent(['kkk-kkk']){
                sh """
                    ansible-playbook ${params.REMOVE_APP}
                """
                }
                }
                post{
                success{
                    echo " successfully Removed APP "
                }
                failure{
                    echo "Failed To Remove APP"
                }
            }
            }
            stage("Deploy DB Container"){
                steps{
                    sshagent(['kkk-kkk']){
                sh """
                    ansible-playbook ${params.DEPLOY_DB}
                """
                }
                }
                post{
                success{
                    echo " successfully DB Deployed"
                }
                failure{
                    echo "Failed To Deploy DB"
                }
            }
            }
            stage("Deploy APP Container"){
                steps{
                    sshagent(['kkk-kkk']){
                sh """
                    ansible-playbook ${params.DEPLOY_APP}
                """
                }
                }
                post{
                success{
                    echo " successfully APP Deployed"
                }
                failure{
                    echo "Failed To Deploy APP"
                }
            }
    }
    stage("Remove Img"){
            steps{
                sshagent(['kkk-kkk']){
                sh '''
                    ansible prod -u ansible -m shell -a "docker rmi -f dhayananthd/app  dhayananthd/db" -b
                '''
                }
            }
            post{
                success{
                    echo " successfully Image Removed"
                }
                failure{
                    echo "Failed To remove Image"
                }
            }
        }
    }    
}
