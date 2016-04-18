(ns ttt.rules-test
  (require [clojure.test :refer :all]
           [ttt.test-helper :refer :all]
           [ttt.board :as board]
           [ttt.rules :refer :all]))


(deftest winning-conditions-test
  (testing "Winning: Top row wins the game."
    (let [player X
          board [X X X
                 E E E
                 E E E]]
      (is (winner? board player))))

  (testing "Winning: Middle row wins the game."
    (let [player X
          board [E E E
                 X X X
                 E E E]]
      (is (winner? board player))))

  (testing "Winning: Bottom row wins the game."
    (let [player X
          board [E E E
                 E E E
                 X X X]]
      (is (winner? board player))))

  (testing "Winning: Left column wins the game."
    (let [player X
          board [X E E
                 X E E
                 X E E]]
      (is (winner? board player))))

  (testing "Winning: Middle column wins the game."
    (let [player X
          board [E X E
                 E X E
                 E X E]]
      (is (winner? board player))))

  (testing "Winning: Right column wins the game."
    (let [player X
          board [E E X
                 E E X
                 E E X]]
      (is (winner? board player))))

  (testing "Winning: Top-left diagonal wins the game."
    (let [player X
          board [X E E
                 E X E
                 E E X]]
      (is (winner? board player))))

  (testing "Winning: Top-right diagonal wins the game."
    (let [player X
          board [E E X
                 E X E
                 X E E]]
      (is (winner? board player))))

  (testing "Draw: All spots are taken, and there is no winner."
    (let [player-one X
          player-two O
          board [X O X
                 O X O
                 O X O]]
      (is (draw? board [player-one player-two]))))

  (testing "Draw: A game with open spots should not be a draw."
    (let [player-one X
          player-two O
          board [X O X
                 O E O
                 X O X]]
      (is (not (draw? board [player-one player-two])))))

  (testing "Draw: A game that a player has won should not be a draw."
    (let [player-one X
          player-two O
          board [X X X
                 X O O
                 O X O]]
      (is (not (draw? board [player-one player-two]))))))

(deftest game-over-test
  (testing "Given a blank board, return false."
    (let [test-board (board/create-board)
          players [X O]]
      (is (not (game-over? test-board players)))))

  (testing "Given a game still in progress, return false."
    (let [test-board [X O E
                      E E E
                      E E E]
          players [X O]]
      (is (not (game-over? test-board players)))))

  (testing "Given a board where a player has won, return true."
    (let [test-board [X X X
                      E E E
                      E E E]
          players [X O]]
      (is (game-over? test-board players))))

  (testing "Given a board that's a draw, return true."
    (let [test-board [X O X
                      O X O
                      O X O]
          players [X O]]
      (is (game-over? test-board players))))

  (testing "Given a board and no players, return true."
    (let [test-board (board/create-board)
          players []]
      (is (game-over? test-board players))))

)
