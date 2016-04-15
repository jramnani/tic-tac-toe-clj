(ns ttt.board-test
  (:require [clojure.test :refer :all]
            [ttt.board :refer :all]))

(deftest board-test
  (testing "Create an empty board."
    (let [test-board (create-board)]
      (is (= 9 (count test-board)))))

  (testing "A valid spot is within the bounds of the board."
    (let [test-board (create-board)
          spot 0
          bad-spot 100]
      (is (valid-spot? test-board spot))
      (is (not (valid-spot? test-board bad-spot)))))

  (testing "A valid spot is not already taken."
    (let [test-board ["X" "" ""
                      ""  "" ""
                      ""  "" ""]
          spot 0]
      (is (not (valid-spot? test-board spot)))))

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

  (testing "Given an empty board, return the index of all spots on the board."
    (let [test-board (create-board)
          expected-spots (into [] (range 9))]
      (is (= expected-spots (available-spots test-board)))))

  (testing "Given a board with a player on it, return the index of avaialble spots on the board."
    (let [test-board (-> (create-board)
                         (take-spot "X" 0))
          expected-spots [1 2 3 4 5 6 7 8]]
      (is (= expected-spots (available-spots test-board)))))

  (testing "Given a board with many players on it, return the index of avaialble spots on the board."
    (let [test-board (-> (create-board)
                         (take-spot "X" 0)
                         (take-spot "O" 1)
                         (take-spot "X" 2))
          expected-spots [3 4 5 6 7 8]]
      (is (= expected-spots (available-spots test-board)))))

  (testing "Winning: Top row wins the game."
    (let [player "X"
          board ["X" "X" "X"
                 ""  ""  ""
                 ""  ""  ""]]
      (is (winner? board player))))

  (testing "Winning: Middle row wins the game."
    (let [player "X"
          board [""  ""  ""
                 "X" "X" "X",
                 ""  ""  ""]]
      (is (winner? board player))))

  (testing "Winning: Bottom row wins the game."
    (let [player "X"
          board [""  ""  ""
                 ""  ""  ""
                 "X" "X" "X"]]
      (is (winner? board player))))

  (testing "Winning: Left column wins the game."
    (let [player "X"
          board ["X" "" ""
                 "X" "" ""
                 "X" "" ""]]
      (is (winner? board player))))

  (testing "Winning: Middle column wins the game."
    (let [player "X"
          board ["" "X" ""
                 "" "X" ""
                 "" "X" ""]]
      (is (winner? board player))))

  (testing "Winning: Right column wins the game."
    (let [player "X"
          board ["" "" "X"
                 "" "" "X"
                 "" "" "X"]]
      (is (winner? board player))))

  (testing "Winning: Top-left diagonal wins the game."
    (let [player "X"
          board ["X"  ""   ""
                 ""   "X"  ""
                 ""   ""   "X"]]
      (is (winner? board player))))

  (testing "Winning: Top-right diagonal wins the game."
    (let [player "X"
          board [""  ""  "X"
                 ""  "X" ""
                 "X" ""  ""]]
      (is (winner? board player))))

  (testing "Draw: All spots are taken, and there is no winner."
    (let [player-one "X"
          player-two "O"
          board ["X" "O" "X"
                 "O" "X" "O"
                 "O" "X" "O"]]
      (is (draw? board [player-one player-two]))))

  (testing "Draw: A game with open spots should not be a draw."
    (let [player-one "X"
          player-two "O"
          board ["X" "O" "X"
                 "O" ""  "O"
                 "O" "X" "O"]]
      (is (not (draw? board [player-one player-two])))))

  (testing "Draw: A game that a player has won should not be a draw."
    (let [player-one "X"
          player-two "O"
          board ["X" "X" "X"
                 "X" "O" "O"
                 "O" "X" "O"]]
      (is (not (draw? board [player-one player-two])))))
)
