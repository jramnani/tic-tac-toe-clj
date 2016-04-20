(ns ttt.board)

;; Board size is the number of spots on the board.
(def board-size 9)

(defn create-board []
  (vec (repeatedly board-size str)))

(defn get-spot [board spot]
  (nth board spot))

(defn get-spots [board spots]
  (map #(get-spot board %1) spots))

(defn valid-spot? [board spot]
  (and (< spot (count board))
       (empty? (get-spot board spot))))

(defn take-spot [board player spot]
  (if (valid-spot? board spot)
    (assoc board spot player)
    board))

(defn available-spots [board]
  (let [available-spots (keep-indexed #(if (empty? %2) %1) board)]
    available-spots))

(defn empty-board? [board]
  (= board-size (count (filter empty? board))))
