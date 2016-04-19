(ns ttt.game
  (require [clojure.string :as string]
           [ttt.board :as board]
           [ttt.display :as display]
           [ttt.rules :as rules]))

(def player-one "X")
(def player-two "O")
(def game-loop-ran (atom nil))
(def game-over (atom nil))

(defn prompt [message-key]
  (condp = message-key
    :pick-spot "Pick a spot"
    :invalid-spot "Invalid spot"
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
  (let [spot (get-ai-move board player)]
    (when spot
      (board/take-spot board player spot))))

(defn get-winner [board players]
  (some #(if (rules/winner? board %1) %1) players))

(defn other-player [player]
  (if (= player-one player)
    player-two
    player-one))

(defn run-game [board players]
  (let [player (first players)
        game-loop-ran (reset! game-loop-ran true)]
    (loop [board board
           player player]
      (cond
        (get-winner board players)
        (do
          (reset! game-over true)
          (println (display/board->str board))
          (println "Game over. Player " (get-winner board players) " wins!"))

        (rules/draw? board players)
        (do
          (reset! game-over true)
          (println (display/board->str board))
          (println "Game over. A draw."))

        :else
        (recur (make-move board player)
               (other-player player))))))
