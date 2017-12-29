#!/usr/bin/env groovy

pipeline {
  agent any

  stages {
    stage("Build & Test") {
      echo "Build using Leiningen"
      sh "lein test"
    }
  }
}
