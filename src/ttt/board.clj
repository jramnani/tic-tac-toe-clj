(ns ttt.board)

(defn create-board []
  (vec (repeatedly 9 str)))

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
