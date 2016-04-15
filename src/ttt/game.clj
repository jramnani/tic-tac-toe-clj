(ns ttt.game
  (require [ttt.board :as board]))


(defn make-computer-move [board player]
  (let [available-spots (board/available-spots board)
        spot (first available-spots)]
    (board/take-spot board player spot)))
