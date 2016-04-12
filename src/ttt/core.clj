(ns ttt.core)

(defn create-board []
  (vec (repeatedly 9 str)))

(defn take-spot [board player spot]
  (if (< spot (count board))
    (assoc board spot player)
    board))

(defn get-spot [board spot]
  (nth board spot))

(defn get-spots [board spots]
  (map #(get-spot board %1) spots))

(defn winner? [board player]
  (let [top-row-spots (get-spots board [0 1 2])]
    (every? #(= player %1) top-row-spots)))
