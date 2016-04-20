(ns ttt.negamax
  (:require [ttt.board :as board]
            [ttt.display :as display]
            [ttt.game :as game]
            [ttt.rules :as rules]))


(defn terminal-node? [board]
  (rules/game-over? board [game/player-one game/player-two]))

(defn node-value [board]
  (if (game/get-winner board [game/player-one game/player-two])
    10
    0))

(defn node-color [player]
  (if (= player game/player-two)
    1
    -1))

(defn node-children [board player]
  "Computes the child states for a given board and player. Returns a list of
  new boards with the player on each spot of the input board's available
  spots."
  (map #(board/take-spot board player %1)
       (board/available-spots board)))

(defn negamax [board depth color]
  (if (terminal-node? board)
    (* color (node-value board))))

(defn negamax-score [board player spot]
  (let [new-board (board/take-spot board player spot)
        next-player (game/other-player player)]
    (println "Computing score for board.")
    (println (display/board->str new-board))
    (if (terminal-node? new-board)
      (let [score (* (node-value new-board)
                     (node-color player))]
        (println "Score: " score)
        score)
      (let [scores (map #(negamax-score new-board next-player %)
                        (board/available-spots new-board))]
        (apply max scores)))))
