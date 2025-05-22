pipeline {
    // agent { label 'ubuntu_local' }
    agent any

    environment {
        BUILD_SCRIPT = './build.sh'
        RUN_SCRIPT = './run.sh'
        OUTPUT_FOLDER = 'out'
        DEPLOY_URL = 'http://localhost:8080'
    }

    stages {
        stage('Build') {
            steps {
                echo '🔨 Running build script...'
                sh "${BUILD_SCRIPT}"
                sleep 60
            }
        }

        stage('Validate Build Output') {
            steps {
                echo '🔍 Checking for output folder.....'
                script {
                    if (!fileExists("${OUTPUT_FOLDER}")) {
                        error("❌ Output folder '${OUTPUT_FOLDER}' not found. Failing the build.")
                    }
                }
            }
        }

        stage('Deploy') {
            when {
                expression {
                    return fileExists("${OUTPUT_FOLDER}")
                }
            }
            steps {
                echo '🚀 Deploying the app....'
                sh "${RUN_SCRIPT}"
            }
        }

        stage('Validate Deployment') {
            steps {
                echo '🌐 Validating deployment on localhost:8080...'
                script {
                    def response = sh(script: "curl -s -o /dev/null -w '%{http_code}' ${DEPLOY_URL}", returnStdout: true).trim()
                    if (response != '200') {
                        error("❌ Deployment validation failed. Expected HTTP 200, got ${response}")
                    } else {
                        echo "✅ Deployment is up and running on ${DEPLOY_URL}"
                    }
                }
            }
        }
    }

    post {
        always {
            echo '🧹 Pipeline execution completed.'
        }
    }
}
