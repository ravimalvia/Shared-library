def call(String githubUrl ){
	pipeline {
		agent { label 'slave'}
		
		environment {
		DOCKERHUB_CRED = credentials('DOCKERHUB')	
		}
		stages{
			stage('Code Download'){
				steps{
				 git "${githubUrl}"		
				}
			}
			stage("Maven build Stage"){
				steps{
				sh 'mvn package'
				}
			}
			stage("Build Image"){
				steps{
				//sh 'ls'
				//sh 'pwd'
				//sh 'whoami'
				sh 'docker build -t java_app:${BUILD_NUMBER} .'
				sh 'docker tag java_app:${BUILD_NUMBER} ravimalvia/java_app:${BUILD_NUMBER}'
				
				}
			}
			stage("Dockerhub login and image push to dockerhub"){
				steps{
				sh 'echo $DOCKERHUB_CRED_PSW | docker login -u $DOCKERHUB_CRED_USR --password-stdin'
				
				sh 'docker push ravimalvia/java_app:${BUILD_NUMBER}'
				}
			}
			stage('Deploy Docker container') {
               			steps {
               			sh "docker run -d -p 8003:8080 ravimalvia/java_app:${BUILD_NUMBER}"
 				}
       			 }
			
		}
	
	}
}