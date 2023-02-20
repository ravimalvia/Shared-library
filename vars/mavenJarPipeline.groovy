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
			stage("Build Step"){
				steps{
				sh 'mvn clean package'
				}
			}
			stage("Test Step"){
				steps{
				sh 'mvn test package'
				}
			}
			stage("Build Image"){
				steps{
				//sh 'ls'
				//sh 'pwd'
				//sh 'whoami'
				sh 'docker build -t ravimalvia/java_app:${BUILD_NUMBER} .'
				
				}
			}
			stage("Dockerhub login and image push to dockerhub"){
				steps{
				sh 'echo $DOCKERHUB_CRED_PSW | docker login -u $DOCKERHUB_CRED_USR --password-stdin'
				sh 'docker push ravimalvia/java_app:${BUILD_NUMBER}'
				}
			}
			
		}
	
	}
}