node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/AntonyJohn/SpringBootRestWS.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'maven'
   }
   stage('Build') {
      // Run the maven build
      withEnv(["MAVEN_HOME=$mvnHome"]) {
          //echo MVN_HOME
         if (isUnix()) {
            sh '"$MAVEN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
         } else {
            bat(/"%MAVEN_HOME%\bin\mvn" clean install -DskipTests/)
         }
      }
   }
   stage('Unit Test') {
        bat(/"%MAVEN_HOME%\bin\mvn" test/)
    }

    stage('Integration Test') {
        bat(/"%MAVEN_HOME%\bin\mvn" verify -DskipUnitTests -Parq-wildfly-swarm/)
    }
   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archiveArtifacts 'target/*.war'
   }
}