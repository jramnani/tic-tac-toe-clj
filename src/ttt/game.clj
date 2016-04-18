(ns ttt.game
  (require [clojure.string :as string]
           [ttt.board :as board]
           [ttt.display :as display]
           [ttt.rules :as rules]))

(def player-one "X")
(def player-two "O")

(defmulti make-move (fn [board player & args] player))
(defmethod make-move player-two
  [board player & args]
  (let [available-spots (board/available-spots board)
        spot (first available-spots)]
    (when spot
      (board/take-spot board player spot)))
  )

(defmethod make-move player-one
  ([board player]
   (make-move board player read-line println))

  ([board player reader writer]
  (writer "Pick a spot")
  (let [input (reader)
        spot (Integer/parseInt (string/trim input))]
    (if (board/valid-spot? board spot)
      (board/take-spot board player spot)
      (do
        (writer "Invalid spot")
        (make-move board player reader writer)))))
  )

(defn get-winner [board players]
  (some #(if (rules/winner? board %1) %1) players))
