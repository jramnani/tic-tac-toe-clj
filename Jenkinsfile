#!/usr/bin/env groovy

pipeline {
  agent {
    docker {
      image 'clojure/lein'
      args '-v $HOME/.m2'
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