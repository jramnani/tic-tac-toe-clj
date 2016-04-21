(ns ttt.test-helper
  (:require [ttt.player :refer :all]))

(def X player-one)
(def O player-two)
(def E "")

(defn create-tied-board []
  [X O X
   O X O
   O X O])
