pipeline {
	agent any

	stages {
		stage('Checkout') {
			steps {
				checkout scm
			}
		}

		stage('Build Docker image') {
			steps {
				sh 'docker build -t conveyor:${BUILD_NUMBER} ./conveyor'
			}
		}

		stage('Import image to k3s') {
			steps {
				sh 'docker save conveyor:${BUILD_NUMBER} | k3s ctr images import -'
			}
		}

		stage('Deploy to Kubernetes') {
			steps {
				sh '''
                  kubectl apply -f k8s/namespace.yml
                  kubectl apply -f k8s/configmap.yml
                  kubectl apply -f k8s/secret.yml
                  kubectl apply -f k8s/postgres.yml
                  kubectl apply -f k8s/conveyor.yml
                  kubectl set image deployment/conveyor conveyor=conveyor:${BUILD_NUMBER} -n credit-conveyor
                  kubectl apply -f k8s/ingress.yml
                '''
			}
		}

		stage('Healthcheck') {
			steps {
				sh '''
                  kubectl rollout status deployment/conveyor -n credit-conveyor --timeout=120s
                  kubectl get pods -n credit-conveyor
                '''
			}
		}
	}
}