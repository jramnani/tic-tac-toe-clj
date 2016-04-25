(ns ttt.negamax-test
  (:require [clojure.test :refer :all]
            [ttt.board :as board]
            [ttt.game :as game]
            [ttt.negamax :refer :all]
            [ttt.player :refer [player-one player-two]]
            [ttt.test-helper :refer :all]))

(defn get-scores [board player]
  (map #(negamax-score board player %1) (board/available-spots board)))

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
  (testing "Given a board where the player has won, return 1."
    (let [test-board [O O O
                      E E E
                      E E E]
          player O]
      (is (= 10 (node-value test-board player)))))

  (testing "Given a board where the player has lost, return -1."
    (let [test-board [X X X
                      E E E
                      E E E]
          player O]
      (is (= -10 (node-value test-board player)))))

  (testing "Given a board where the game is a draw, the value should be 0"
    (let [test-board (create-tied-board)
          player O]
    (is (= 0 (node-value test-board player)))))
)

(deftest negamax-score-test
  (testing "A spot where the player can win should be scored as the best move."
    (let [test-board [O O E
                      X X E
                      E E E]
          player O
          spot 2
          scores (get-scores test-board player)
          best-score (first scores)]
      (is (= best-score (negamax-score test-board player spot)))))

  (testing "A spot where the player can block the opponent should be scored as the second best move."
    (let [test-board [O O E
                      X X E
                      E E E]
          player O
          spot 5
          scores (get-scores test-board player)
          second-best-score (second scores)]
      (is (= second-best-score (negamax-score test-board player spot)))
      ))

  (testing "A spot that generates a draw should be scored at 0."
    (let [test-board [O X O
                      X O X
                      X O E]
          player X
          spot 8]
      (is (= 0 (negamax-score test-board player spot)))))

  (testing "Given a game with two open spaces, score the winning move at 10."
    (let [test-board [E O E
                      O X O
                      O X X]
          player O
          winning-spot 0]
      (is (= 10 (negamax-score test-board player 0)))))

  (testing "Given a game with two open spaces, score the losing move at -9."
    (let [test-board [E O E
                      O X O
                      O X X]
          player O
          losing-spot 2]
      (is (= -9 (negamax-score test-board player losing-spot)))))

)

(deftest get-ai-move-test
  (testing "Given an empty board, choose one of the best spots (corners or the middle)."
    (let [test-board (board/create-board)
          player O
          best-spots #{0 2 4 6 8}]
      (is (contains? best-spots (get-ai-move test-board player)))))

  (testing "Given a board with one spot available, return the index for that spot."
    (let [test-board [X O X
                      O X O
                      O X E]
          player O
          expected-spot 8]
      (is (= expected-spot (get-ai-move test-board player)))))

  (testing "Given a board with two spots available, and a winning move is available, then return the index for the winning spot."
    (let [test-board [E X E
                      O O X
                      X O O]
          player O
          winning-spot 0]
      (is (= winning-spot (get-ai-move test-board player)))))

  (testing "Given a board where the opponent chooses a corner, choose the middle to prevent a fork."
    (let [test-board [X E E
                      E E E
                      E E E]
          player O
          expected-spot 4]
      (is (= expected-spot (get-ai-move test-board player)))))

  (testing "Given a board with two spots available, and a blocking move is available, then return the index for the blocking spot."
    (let [test-board [E O E
                      X X O
                      O X X]
          player O
          blocking-spot 0]
      (is (= blocking-spot (get-ai-move test-board player)))))

  (testing "Given a board with three spots available, try to pick the best spot you can."
    (let [test-board [X O X
                      O X O
                      E E E]
          player O
          expected-spots #{6 8}]
      (is (contains? expected-spots (get-ai-move test-board player)))))
)
