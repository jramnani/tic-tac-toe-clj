(ns ttt.game
  (require [clojure.string :as string]
           [ttt.board :as board]
           [ttt.display :as display]
           [ttt.negamax :as negamax]
           [ttt.player :refer [player-one player-two other-player]]
           [ttt.rules :as rules]))

(def game-loop-ran (atom nil))
(def game-over (atom nil))

(defn prompt [message-key]
  (condp = message-key
    :pick-spot "Pick a spot"
    :invalid-spot "Invalid spot"
    :prelude "Welcome to Tic-Tac-Toe!"
    nil))

(defn get-human-move
  "Prompt the user to pick a spot. Return the user's choice as an Integer."
  [board player reader writer]
  (writer (display/board->str board))
  (writer (prompt :pick-spot))
  (let [input (reader)
        spot (Integer/parseInt (string/trim input))]
    (if (board/valid-spot? board spot)
      spot
      (do
        (writer (prompt :invalid-spot))
        (get-human-move board player reader writer)))))

(defn get-ai-move [board player]
  (let [available-spots (board/available-spots board)
        spot (first available-spots)]
    (when spot
      spot)))

(defmulti make-move (fn [board player & args] player))

(defmethod make-move player-one
  ([board player]
   (make-move board player read-line println))

  ([board player reader writer]
   (let [spot (get-human-move board player reader writer)]
     (board/take-spot board player spot))))

(defmethod make-move player-two
  [board player & args]
  (let [spot (negamax/get-ai-move board player)]
    (when spot
      (board/take-spot board player spot))))

(defn run-game [board players]
  (let [player (first players)
        game-loop-ran (reset! game-loop-ran true)]
    (println (prompt :prelude))
    (loop [board board
           player player]
      (cond
        (rules/get-winner board players)
        (do
          (reset! game-over true)
          (println (display/board->str board))
          (println "Game over. Player " (rules/get-winner board players) " wins!"))

        (rules/draw? board players)
        (do
          (reset! game-over true)
          (println (display/board->str board))
          (println "Game over. A draw."))

        :else
        (recur (make-move board player)
               (other-player player))))))
