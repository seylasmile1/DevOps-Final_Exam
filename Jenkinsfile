pipeline {
    agent any

    triggers {
        // Poll SCM every 5 minutes. 
        // Jenkins prefers 'H/5' over '*/5' to evenly distribute load.
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                // Replace with your actual build command (e.g., npm install, ./mvnw clean package)
                sh 'echo "Simulating build process..."'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running automated tests...'
                // Replace with your actual test command (e.g., npm test, ./mvnw test)
                sh 'echo "Simulating tests..."'
            }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deploying to Web Server using Ansible...'
                // Ensure your Ansible playbook (e.g., deploy.yml) is in the repository
                ansiblePlaybook(
                    playbook: 'deploy.yml',
                    inventory: 'inventory.ini', // Path to your inventory file
                    credentialsId: 'web-server-ssh-key', // The ID of the SSH key stored in Jenkins credentials
                    colorized: true,
                    hostKeyChecking: false 
                )
            }
        }
    }

    post {
        failure {
            echo 'Build or Test failed. Triggering email notifications...'
            emailext(
                subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>The build failed.</p>
                         <p>Check console output at <a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""",
                to: 'srengty@gmail.com',
                recipientProviders: [
                    // Sends email to the specific user who committed the breaking change
                    [$class: 'CulpritsRecipientProvider'],
                    // Sends email to the user who triggered the build (if done manually)
                    [$class: 'RequesterRecipientProvider']
                ],
                mimeType: 'text/html'
            )
        }
        success {
            echo 'Pipeline completed and deployed successfully!'
        }
    }
}