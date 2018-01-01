(ns ttt.failing-test
  (:require [clojure.test :refer :all]
            [ttt.test-helper :refer :all]
            [ttt.board :refer :all]))

(deftest jenkins-failure
  (testing "A failing test in Jenkins")
    (is true false))