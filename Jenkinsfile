#!/usr/bin/env groovy

pipeline {

    agent {
        docker {
            image 'clojure:lein-2.8.1'
        }
    }

  stages {

    stage("Build & Test") {
      steps {
        sh "lein with-profile test test2junit"
      }
    }
  }

  post {
    always {
      junit(testResults: "target/junit/**/*.xml", allowEmptyResults: true)
    }
  }
}
