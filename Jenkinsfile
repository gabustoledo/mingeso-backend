pipeline {
	agent any

	tools{
		gradle 'gradle-6.8.3'
	}
    environment{
        DOCKERHUB_CREDENTIALS = credentials('gabustoledo-dockerhub')
    }

    stages {

        stage('JUnit'){
            steps {
			    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
					dir("/var/lib/jenkins/workspace/backend/backend") {
						sh 'chmod +x ./gradlew'
                        sh './gradlew test'
					}
                }
		    }
        }

        stage('SonarQube'){
            steps{
                dir("/var/lib/jenkins/workspace/backend/backend"){
                    withSonarQubeEnv('sonarcloud_backend_gabriel'){
                        sh 'chmod +x ./gradlew'
                        sh './gradlew sonarqube'
                    }    
                }                
            }
        }

        stage('Docker Build'){
            steps{
                dir("/var/lib/jenkins/workspace/backend/backend"){
                    sh 'docker build --build-arg JAR_FILE=build/libs/*.jar -t gabustoledo/repo-back .'
                }        
            }
        }
        stage('Login'){
            steps{
                sh  'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Docker Hub'){
            steps{
                dir("/var/lib/jenkins/workspace/backend/backend"){
                    sh 'docker push gabustoledo/repo-back'
                }
            }
        }
    }
    post{
        always{
            sh 'docker logout'
        }
    }
}