def call(String githubUrl ){
	pipeline {
		agent { label 'slave'}
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
			stage("fourth"){
				steps{
				echo "its fourth steps"
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