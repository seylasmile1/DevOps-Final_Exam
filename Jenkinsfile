pipeline {

    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('SQLite Test') {
            steps {
                sh 'mvn test -Dspring.profiles.active=test'
            }
        }

        stage('Deploy') {
            steps {
                sh 'ansible-playbook -i inventory.ini deploy.yml'
            }
        }
    }

    post {

        failure {
            emailext(
                subject: "Build Failed",
                body: "See ${BUILD_URL}",
                to: "srengty@gmail.com"
            )
        }
    }
}
