(ns ttt.display-test
  (:require [clojure.test :refer :all]
            [ttt.core :refer :all]
            [ttt.display :refer :all]))

(def empty-board-string
"|_0_|_1_|_2_|
|_3_|_4_|_5_|
|_6_|_7_|_8_|")

(deftest display-test
  (testing "Display an empty board"
    (let [test-board (create-board)]
      (is (= empty-board-string (board->str test-board)))))

  (testing "Given an row with no elements, return the empty string"
    (let [empty-row []
          expected-str ""]
      (is (= expected-str (row->str empty-row)))))

  (testing "Convert an empty board row into a display row"
    (let [test-row [[0 ""] [1 ""] [2 ""]]
          expected-str "|_0_|_1_|_2_|"]
      (is (= expected-str (row->str test-row)))))

  (testing "Display a row with players on it"
    (let [test-row ["X", "O", "X"]
          expected-str "|_X_|_O_|_X_|"]
      (is (= expected-str (row->str test-row)))))

  (testing "Given a spot on the board, return a string to represent the player on the spot."
    (let [player-one [4 "X"]
          player-two [4 "O"]
          no-player [4 ""]
          player-one-str "_X_"
          player-two-str "_O_"
          no-player-str "_4_"]
      (is (= player-one-str (spot->str player-one)))
      (is (= player-two-str (spot->str player-two)))
      (is (= no-player-str (spot->str no-player))))))

