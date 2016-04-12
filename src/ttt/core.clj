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

(defn- same-player-on-spots? [player spots]
  (every? #(= player %1) spots))

(defn winner? [board player]
  (let [player-wins? (partial same-player-on-spots? player)
        top-row-spots (get-spots board [0 1 2])
        middle-row-spots (get-spots board [3 4 5])
        bottom-row-spots (get-spots board [6 7 8])
        top-left-diagonal-spots (get-spots board [0 4 8])
        top-right-diagonal-spots (get-spots board [2 4 6])
        ]
    (or (player-wins? top-row-spots)
        (player-wins? middle-row-spots)
        (player-wins? bottom-row-spots)
        (player-wins? top-left-diagonal-spots)
        (player-wins? top-right-diagonal-spots)
        )))
