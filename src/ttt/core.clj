(ns ttt.core)

(defn create-board []
  (vec (repeatedly 9 str)))

(defn take-spot [board player spot]
  (assoc board spot player))
