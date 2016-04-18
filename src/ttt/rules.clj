(ns ttt.rules
  (require [ttt.board :as board]))


(defn- same-player-on-spots? [player spots]
  (every? #(= player %1) spots))

(defn winner? [board player]
  (let [player-wins? (partial same-player-on-spots? player)
        top-row-spots (board/get-spots board [0 1 2])
        middle-row-spots (board/get-spots board [3 4 5])
        bottom-row-spots (board/get-spots board [6 7 8])
        left-column-spots (board/get-spots board [0 3 6])
        middle-column-spots (board/get-spots board [1 4 7])
        right-column-spots (board/get-spots board [2 5 8])
        top-left-diagonal-spots (board/get-spots board [0 4 8])
        top-right-diagonal-spots (board/get-spots board [2 4 6])
        ]
    (or (player-wins? top-row-spots)
        (player-wins? middle-row-spots)
        (player-wins? bottom-row-spots)
        (player-wins? left-column-spots)
        (player-wins? middle-column-spots)
        (player-wins? right-column-spots)
        (player-wins? top-left-diagonal-spots)
        (player-wins? top-right-diagonal-spots))))

(defn draw? [board players]
  (and (not-any? #(empty? %1) board)
       (not-any? #(winner? board %1) players)))
