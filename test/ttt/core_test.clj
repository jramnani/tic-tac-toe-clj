(ns ttt.core-test
  (:require [clojure.test :refer :all]
            [ttt.core :refer :all]))

(deftest board-test
  (testing "Create an empty board."
    (let [test-board (create-board)]
      (is (= 9 (count test-board)))))

  (testing "Take a spot on the board"
    (let [test-board (create-board)
          spot 1
          player "X"
          new-board (take-spot test-board player spot)]
      (is (= player (nth new-board spot))))))
