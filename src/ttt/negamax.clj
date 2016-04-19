(ns ttt.negamax
  (:require [ttt.board :as board]
            [ttt.game :refer [get-winner player-one player-two]]
            [ttt.rules :as rules]))


(defn terminal-node? [board]
  (rules/game-over? board [player-one player-two]))

(defn node-value [board]
  (if (get-winner board [player-one player-two])
    10
    0))

(defn node-color [player]
  (if (= player player-two)
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
