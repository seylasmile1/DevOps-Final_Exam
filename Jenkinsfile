pipeline {

agent any

triggers {
    pollSCM('H/5 * * * *')
}

stages {

    stage('Checkout') {
        steps {
            checkout scm
        }
    }

    stage('Build') {
        steps {
            sh 'mvn clean package -DskipTests'
        }
    }

    stage('Test') {
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
            subject: "Build Failed - ${JOB_NAME}",
            body: """

Build failed.

Job:
${JOB_NAME}

Build URL:
${BUILD_URL}
""",

            to: "srengty@gmail.com,seyla00004@gmail.com",

            recipientProviders: [
                [$class: 'DevelopersRecipientProvider']
            ]
        )
    }
}

}
