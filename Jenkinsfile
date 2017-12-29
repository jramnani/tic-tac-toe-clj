#!/usr/bin/env groovy

pipeline {
  agent any

  stages {

    stage("Build & Test") {
      steps {
        sh "lein with-profile test test2junit"
        junit(testResults: "target/junit/**/*.xml", allowEmptyResults: true)
      }
    }
  }
}
