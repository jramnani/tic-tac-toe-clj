#!/usr/bin/env groovy

pipeline {
  agent any

  stages {

    stage("Build & Test") {
      steps {
        sh "lein with-profile test test2junit"
      }
    }

    stage("Human Input") {
      steps {
        input "Shall I continue?"
      }
    }
  }

  post {
    always {
      junit(testResults: "target/junit/**/*.xml", allowEmptyResults: true)
    }
  }
}
