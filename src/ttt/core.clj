(ns ttt.core
  (:require [ttt.board :as board]
            [ttt.game :as game]))


(defn -main []
  (def board (board/create-board))
  (def players [game/player-one game/player-two])
  (game/run-game board players))
