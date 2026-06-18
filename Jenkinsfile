pipeline {
    agent any

    triggers {
        // Poll SCM every 5 minutes
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Build & Test') {
            steps {
                echo 'Building and testing the Spring Boot application...'
                // Make the Maven wrapper executable
                sh 'chmod +x mvnw'
                // Run clean, test, and package
                sh './mvnw clean package'
            }
        }
        
        stage('Deploy to Web Server') {
            steps {
                echo 'Deploying application via Ansible...'
                // Run the Ansible playbook. 
                // Note: Update the path to your inventory and playbook files if they differ.
                sh 'ansible-playbook -i inventory deploy.yml'
            }
        }
    }

    post {
        failure {
            echo 'Pipeline failed! Sending email notifications...'
            // Send email to the specific address AND the developers who committed the code
            emailext (
                subject: "Build Failed: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>The build or test process failed for Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'.</p>
                         <p>Please check the console output here: <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></p>""",
                to: 'seyla00004@gmail.com',
                // srengty@gmail.com,
                recipientProviders: [
                    [$class: 'DevelopersRecipientProvider'],
                    [$class: 'CulpritsRecipientProvider']
                ]
            )
        }
        success {
            echo 'Build, Test, and Deployment completed successfully!'
        }
    }
}