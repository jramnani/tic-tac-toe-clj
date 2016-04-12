(ns ttt.core-test
  (:require [clojure.test :refer :all]
            [ttt.core :refer :all]))

(deftest board-test
  (testing "Create an empty board."
    (let [test-board (create-board)]
      (is (= 9 (count test-board))))))
