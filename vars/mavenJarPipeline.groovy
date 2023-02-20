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
				sh 'ls'
				sh 'pwd'
				sh 'whoami'
				}
			}
			stage("Dockerhub login"){
				steps{
				sh 'echo "DOCKERHUB_CRED_PSW | docker login -u $DOCKERHUB_CRED_USR --password-stdin'
				}
			}
		}
	
	}
}