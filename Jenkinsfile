pipeline {
    agent any
	
	environment {
		DOCKER = credentials("docker")
	}
	
    stages {
        stage("Build") {
            steps {
            	sh "docker build --privileged -t hit-web-server:latest webServer"
                sh "docker build --privileged -t automation-qa:latest webServer"
            }
        }
        stage("Test") {
            steps {
                sh "docker ps --privileged -f name=automation-qa-container -q | xargs --no-run-if-empty docker container stop"
                sh "docker container ls --privileged -a -f name=automation-qa-container -q | xargs -r docker container rm"
                sh "docker run --privileged -d -p 8000:80 --name automation-qa-container automation-qa:latest"
            	sh "cd automation && ./gradlew test --info"
                junit allowEmptyResults: true, testResults: "**/test/TEST-*.xml"
            }
        }
        stage("Deploy") {
            steps {
                sh "docker ps --privileged -f name=hit-web-server-container -q | xargs --no-run-if-empty docker container stop"
                sh "docker container ls --privileged -a -f name=hit-web-server-container -q | xargs -r docker container rm"
                sh "docker run --privileged -d -p 80:80 --name hit-web-server-container hit-web-server:latest"
                sh "echo $DOCKER_PSW | docker login -u $DOCKER_USR --password-stdin"
                sh "docker commit --privileged hit-web-server-container danielwein/hit-web-server"
                sh "docker push --privileged danielwein/hit-web-server"
            }
        }
    }
}
