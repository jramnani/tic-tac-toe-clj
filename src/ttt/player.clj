(ns ttt.player)

(def player-one "X")
(def player-two "O")

(defn other-player [player]
  (if (= player-one player)
    player-two
    player-one))

