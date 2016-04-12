(ns ttt.core)

(defn create-board []
  (vec (repeatedly 9 str)))

(defn take-spot [board player spot]
  (if (< spot (count board))
    (assoc board spot player)
    board))
