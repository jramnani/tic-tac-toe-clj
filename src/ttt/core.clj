(ns ttt.core
  (:require [ttt.board :as board]
            [ttt.game :as game]
            [ttt.player :refer [player-one player-two]]))


(defn -main []
  (def board (board/create-board))
  (def players [player-one player-two])
  (game/run-game board players))
