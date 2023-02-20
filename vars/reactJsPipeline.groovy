def call(String githubUrl ){
	pipeline {
		agent { label 'slave1'}
		environment {
                DOCKERHUB_CRED = credentials('DOCKERHUB')
                }

		stages{
			stage('Code Download'){
				steps{
				 git "${githubUrl}"		
				}
			}
			stage("build"){
				steps{
				sh 'sudo docker build -t react_app:${BUILD_NUMBER}'
				sh 'sudo docker tag react_app:${BUILD_NUMBER} ravimalvia/react_app:${BUILD_NUMBER}'
				}
			}
			
			stage("docker login and upload to dockerhub"){
				steps{
				sh 'sudo echo $DOCKERHUB_CRED_PSW | docker login -u $DOCKERHUB_CRED_USR --password-stdin'
				sh 'sudo docker push ravimalvia/react_app:${BUILD_NUMBER}'
				}
			}
			stage("deploy"){
				steps{
				sh 'docker run -dit -p 3000:3000 --name webapp react_app:${BUILD_NUMBER}'
				}
			}
			
		
		}
	
	}
}