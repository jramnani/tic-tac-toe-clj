(ns ttt.game
  (require [clojure.string :as string]
           [ttt.board :as board]
           [ttt.rules :as rules]))

(def player-one "X")
(def player-two "O")

(defn make-computer-move [board player]
  (let [available-spots (board/available-spots board)
        spot (first available-spots)]
    (board/take-spot board player spot)))

(defn make-human-move [board player reader writer]
  (writer "Pick a spot")
  (let [input (reader)
        spot (Integer/parseInt (string/trim input))]
    (if (board/valid-spot? board spot)
      (board/take-spot board player spot)
      (do
        (writer "Invalid spot")
        (make-human-move board player reader writer)))))

(defn get-winner [board players]
  (some #(if (rules/winner? board %1) %1) players))
