(ns ttt.negamax-test
  (:require [clojure.test :refer :all]
            [ttt.board :as board]
            [ttt.game :as game]
            [ttt.negamax :refer :all]
            [ttt.test-helper :refer :all]))

(deftest terminal-node-test
  (testing "Given a game in progress, return false."
    (let [test-board [X E E
                      E E E
                      E E E]]
      (is (= false (terminal-node? test-board)))))

  (testing "Given a game where a player has won, return true."
    (let [test-board [X X X
                      E E E
                      E E E]]
      (is (= true (terminal-node? test-board)))))

  (testing "Given a game that's a draw, return true."
    (is (= true (terminal-node? (create-tied-board))))
    )
)

(deftest node-value-test
  (testing "Given a board where a player has won, the value should be 10"
    (let [test-board [O O O
                      E E E
                      E E E]]
      (is (= 10 (node-value test-board)))))

  (testing "Given a board where the game is a draw, the value should be 0"
    (is (= 0 (node-value (create-tied-board)))))
)

(deftest node-color-test
  (testing "Given player-one, the color should be -1."
    (is (= -1 (node-color game/player-one))))

  (testing "Given player-two, the color should be 1."
    (is (= 1 (node-color game/player-two))))

)

(deftest node-children-test
  (testing "Given an empty board, there should be nine children."
    (let [test-board (board/create-board)
          player O
          expected-children 9]
      (is (= expected-children (count (node-children test-board player))))))

  (testing "Given an empty board, the result should be a list of new boards with the player on each spot."
    (let [test-board (board/create-board)
          player O
          ;; test a sample of the results.
          expected-board-1 [O E E
                            E E E
                            E E E]
          expected-board-2 [E O E
                            E E E
                            E E E]
          expected-board-3 [E E O
                            E E E
                            E E E]
          actual-children (node-children test-board player)]
      (is (some #{expected-board-1} actual-children))
      (is (some #{expected-board-2} actual-children))
      (is (some #{expected-board-3} actual-children))))
)

;; In these tests player-two, "O", is using negamax to find the best move.
(deftest negamax-test
  (testing "Given a game where player-one wins, the value should be -10."
    (let [test-board [X X X
                      E E E
                      E E E]
          color -1
          depth 0]
      (is (= -10 (negamax test-board depth color)))))

  (testing "Given a game where player-two wins, the value should be 10."
    (let [test-board [O O O
                      E E E
                      E E E]
          color 1
          depth 0]
      (is (= 10 (negamax test-board depth color)))))

  (testing "Given a game that ends in a draw, the value should be 0."
    (let [test-board (create-tied-board)
          color 1
          depth 0]
      (is (= 0 (negamax test-board depth color)))))
)
