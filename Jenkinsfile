#!/usr/bin/env groovy

pipeline {
  agent any

  stages {

    stage("Build & Test") {
      steps {
        echo "Build using Leiningen"
        sh "lein test"
      }
    }
  }
}
