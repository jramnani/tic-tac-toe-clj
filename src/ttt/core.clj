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

;(defn winner? [board player]
  ;(let [top-row-winner (every? #(= player %1)
                               ;(vec (nth board 0)
                                    ;(nth board 1)
                                    ;(nth board 2)))]
    ;top-row-winner))
