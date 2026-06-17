pipeline {

```
agent any

triggers {
    pollSCM('H/5 * * * *')
}

stages {

    stage('Build') {
        steps {
            sh '''
            mvn clean package -DskipTests
            '''
        }
    }

    stage('SQLite Test') {
        steps {
            sh '''
            mvn test -Dspring.profiles.active=test
            '''
        }
    }

    stage('Deploy') {
        steps {
            sh '''
            ansible-playbook \
            -i inventory.ini \
            deploy.yml
            '''
        }
    }
}

post {

    success {
        echo 'Deployment Successful'
    }

    failure {

        emailext(
            subject: "[FAILED] ${JOB_NAME} #${BUILD_NUMBER}",
            body: """
```

Build failed.

Job:
${JOB_NAME}

Build:
${BUILD_URL}

Commit:
${GIT_COMMIT}
""",

```
            to: "srengty@gmail.com, seyla00004@gmail.com",

            recipientProviders: [
                [$class: 'DevelopersRecipientProvider']
            ]
        )
    }
}
```

}
