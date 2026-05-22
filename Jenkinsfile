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
				sh 'docker build -t conveyor:1.0 ./conveyor'
			}
		}

		stage('Import image to k3s') {
			steps {
				sh 'docker save conveyor:1.0 | k3s ctr images import -'
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
                  kubectl apply -f k8s/ingress.yml
                  kubectl rollout restart deployment/conveyor -n credit-conveyor
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