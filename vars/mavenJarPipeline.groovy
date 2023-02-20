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
			stage("dockerhub login"){
				steps{
				echo "$DOCKERHUB_CRED"
				}
			}
			stage("fifth"){
				steps{
				echo "its fifth steps"
				}
			}
		}
	
	}
}