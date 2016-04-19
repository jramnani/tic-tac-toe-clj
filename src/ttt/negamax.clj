(ns ttt.negamax
  (:require [ttt.game :refer [get-winner player-one player-two]]
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
