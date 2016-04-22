(ns ttt.negamax
  (:require [ttt.board :as board]
            [ttt.display :as display]
            [ttt.player :refer [player-one player-two other-player]]
            [ttt.rules :as rules]))


(defn terminal-node? [board]
  (rules/game-over? board [player-one player-two]))

(defn node-value [board player]
  (cond
    (rules/winner? board player) 10
    (rules/winner? board (other-player player)) -10
    :else 0))

(defn node-color [player]
  (if (= player player-two)
    1
    -1))

(defn negamax-score [board player spot & {:keys [debug] :or {debug false}}]
  (let [new-board (board/take-spot board player spot)
        next-player (other-player player)]
    (when debug
      (println "Given board.")
      (println (display/board->str board))
      (println "New board.")
      (println "Place player " player " on spot " spot)
      (println (display/board->str new-board)))
    (if (terminal-node? new-board)
      (let [score (* (node-value new-board player)
                     (node-color player))]
        (when debug
          (println "Score: " score))
        score)
      (->> (board/available-spots new-board)
           (map #(negamax-score new-board next-player % :debug debug))
           (apply max)))))

(defn get-ai-move [board player]
  (let [preferred-starting-spots [0 2 4 6 8]]
    (if (board/empty-board? board)
      (rand-nth preferred-starting-spots)
      (let [available-spots (board/available-spots board)]
        (->> available-spots
             (map #(negamax-score board player %))
             ;; join the list of available spots, with their scores. '((spot, score) ...)
             (map list available-spots)
             ;; sort the list of (spot, score) by the score
             (sort-by second)
             ;; sort the list in descending order.
             (reverse)
             ;; Of the sorted list of '((spot, score) ...), we want the first spot.
             (ffirst))))))
