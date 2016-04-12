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
             (take-spot test-board player bad-spot)))))

  (testing "Given a spot on the board, return the player on that spot."
    (let [initial-board (create-board)
          player "X"
          spot 1
          test-board (take-spot initial-board player spot)]
      (is (= player
             (get-spot test-board spot)))))

  (testing "Given an empty spot on the board, return the empty string."
    (let [initial-board (create-board)
          spot 1
          expected-player ""]
      (is (= "" (get-spot initial-board spot)))))

  (testing "Given a spot that's not on the board, throw an exception."
    (let [initial-board (create-board)
          spot 100]
      (is (thrown? IndexOutOfBoundsException
                   (get-spot initial-board spot)))))

  (testing "Given mutiple spots on the board, return a list of the players at those spots."
    (let [test-board ["X" "X" "X"
                      ""  ""  ""
                      ""  ""  ""
                      ]
          spots [0 1 2]
          ]
      (is (= '("X" "X" "X")
             (get-spots test-board spots)))))

  (testing "Given an empty list of spots, return an empty list."
    (let [test-board ["X" "X" "X"
                      ""  ""  ""
                      ""  ""  ""]
          spots []]
      (is (= '()
             (get-spots test-board spots)))))

  ;(testing "Winning: Top row wins the game."
    ;(let [player "X"
          ;board ["X","X","X",
                 ;"", "", "",
                 ;"", "", ""]]
      ;(is (winner? player board))))
  )
