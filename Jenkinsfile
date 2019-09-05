pipeline {
    agent any
    environment {
      // Get the Maven tool
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'maven'
    }
    stages {
        stage('Preparation') { 
            steps {
                // for display purposes
                // Get some code from a GitHub repository
                git 'https://github.com/AntonyJohn/SpringBootRestWS.git'
            }
        }
        stage('Build') {
            steps {
                // Run the maven build
                withEnv(["MAVEN_HOME=$mvnHome"]) {
                    script {
                        //echo MVN_HOME
                        if (isUnix()) {
                            sh '"$MAVEN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
                        } else {
                            bat(/"%MAVEN_HOME%\bin\mvn" clean install -DskipTests/)
                        }
                    }
                }
            }
        }
        stage('Unit Test') {
            steps {
                bat(/"%MAVEN_HOME%\bin\mvn" test/)
            }
        }
        stage('Integration Test') {
            steps {
                bat(/"%MAVEN_HOME%\bin\mvn" verify -DskipUnitTests -Parq-wildfly-swarm/)
            }
        }
        stage('Results') {
           steps {
                junit '**/target/surefire-reports/TEST-*.xml'
                archiveArtifacts 'target/*.war'
           }
        }
    }
    post {
        always {
          script {
            if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {                
                emailext (
                  subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                  body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                  mimeType: 'text/html',
                  recipientProviders: [[$class: 'RequesterRecipientProvider'], [$class: 'DevelopersRecipientProvider']]
                )
            }
          }
        }
    }
}