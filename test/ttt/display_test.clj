(ns ttt.display-test
  (:require [clojure.test :refer :all]
            [ttt.board :refer :all]
            [ttt.display :refer :all]))

(def empty-board-string
"|_0_|_1_|_2_|
|_3_|_4_|_5_|
|_6_|_7_|_8_|")

(deftest display-test
  (testing "Display an empty board"
    (let [test-board (create-board)]
      (is (= empty-board-string (board->str test-board)))))

  (testing "Given a row with no elements, return the empty string"
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

  (testing "Given an empty spot, show the spot's index on the board."
    (let [spot [4 ""]
          expected-str "_4_"]
      (is (= expected-str (spot->str spot)))))

  (testing "Given a spot with a player on it, show the player on the board."
    (let [spot [4 "X"]
          expected-str "_X_"]
      (is (= expected-str (spot->str spot))))))

