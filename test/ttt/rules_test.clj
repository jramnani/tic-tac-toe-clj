(ns ttt.rules-test
  (require [clojure.test :refer :all]
           [ttt.board :as board]
           [ttt.rules :refer :all]))


(deftest winning-conditions-test
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
      (is (not (draw? board [player-one player-two]))))))
