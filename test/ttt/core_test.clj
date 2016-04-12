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
      (is (= player (nth new-board spot)))))

  (testing "Given a spot that's not on the board, return the existing board."
    (let [test-board (create-board)
          player "X"
          bad-spot 100]
      (is (= test-board
             (take-spot test-board player bad-spot))))))
