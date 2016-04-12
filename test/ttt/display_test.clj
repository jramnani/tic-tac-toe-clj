(ns ttt.display-test
  (:require [clojure.test :refer :all]
            [ttt.core :refer :all]
            [ttt.display :refer :all]))

(def empty-board-string
  "|___|___|___|
|___|___|___|
|___|___|___|")

(deftest display-test
  (testing "Display an empty board"
    (let [test-board (create-board)]
      (is (= empty-board-string (board->str test-board)))))

  (testing "Convert an empty board row into a display row"
    (let [test-row ["" "" ""]
          expected-str "|___|___|___|"]
      (is (= expected-str (row->str test-row)))))

  (testing "Display a row with players on it"
    (let [test-row ["X", "O", "X"]
          expected-str "|_X_|_O_|_X_|"]
      (is (= expected-str (row->str test-row)))))

  (testing "Given a spot on the board, return a string to represent the player on the spot."
    (let [player-one "X"
          player-two "O"
          no-player ""
          player-one-str "_X_"
          player-two-str "_O_"
          no-player-str "___"]
      (is (= player-one-str (spot->str player-one)))
      (is (= player-two-str (spot->str player-two)))
      (is (= no-player-str (spot->str no-player))))))


