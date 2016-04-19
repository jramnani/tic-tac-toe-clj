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
