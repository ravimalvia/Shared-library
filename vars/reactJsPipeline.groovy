def call(String githubUrl ){
	pipeline {
		agent { label 'slave'}
		stages{
			stage('Code Download'){
				steps{
				 git "${githubUrl}"		
				}
			}
			stage("second"){
				steps{
				echo "its second steps"
				}
			}
			stage("third"){
				steps{
				echo "its third steps"
				}
			}
			stage("fourth"){
				steps{
				echo "its fourth steps"
				}
			}
			stage('second){
				steps{
				echo "its second steps"
				}
			}
		}
	
	}
}