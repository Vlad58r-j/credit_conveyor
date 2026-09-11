pipeline {
	agent any

	stages {
		stage('Checkout') {
			steps {
				checkout scm
			}
		}

		stage('Build JAR') {
			steps {
				dir('conveyor') {
					sh 'chmod +x ./gradlew'
					sh './gradlew clean bootJar -x test'
				}
			}
		}

		stage('Build Docker image') {
			steps {
				sh 'docker compose build conveyor'
			}
		}

		stage('Deploy') {
			steps {
				sh 'docker compose up -d conveyor'
			}
		}

		stage('Healthcheck') {
			steps {
				sh '''
				  for i in $(seq 1 30); do
					if curl -fsS http://172.17.0.1:8080/actuator/health; then
					  echo "Application is ready"
					  exit 0
					fi
					echo "Waiting... $i"
					sleep 2
				  done

				  echo "Application did not become ready"
				  docker logs conveyor --tail=100
				  exit 1
        '''
			}
		}
	}
}
