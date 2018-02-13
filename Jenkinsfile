#!/usr/bin/env groovy

pipeline {
  agent {
    label 'docker'
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