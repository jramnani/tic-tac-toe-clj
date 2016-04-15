(ns ttt.game
  (require [clojure.string :as string]
           [ttt.board :as board]))


(defn make-computer-move [board player]
  (let [available-spots (board/available-spots board)
        spot (first available-spots)]
    (board/take-spot board player spot)))

(defn make-human-move [board player reader writer]
  (writer "Pick a spot")
  (let [input (reader)
        spot (Integer/parseInt (string/trim input))]
    (if (board/valid-spot? board spot)
      board
      (do
        (writer "Invalid spot")
        (make-human-move board player reader writer)))))
