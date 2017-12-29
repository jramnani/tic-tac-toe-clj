(defproject ttt "0.1.0-SNAPSHOT"
  :description "A Tic Tac Toe game."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :profiles {:test {:plugins [[test2junit "1.3.3"]]}}
  :aliases { "coverage" ["cloverage" "--ns-exclude-regex" "ttt\\.core"] }
  :test2junit-output-dir "target/junit"
  :test2junit-run-ant false
  :main ttt.core)
