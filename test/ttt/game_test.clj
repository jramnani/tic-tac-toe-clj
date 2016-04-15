(ns ttt.game-test
  (require [clojure.test :refer :all]
           [ttt.board :as board]
           [ttt.game :refer :all]))

(deftest make-computer-move-test
  (testing "Dumb AI: Computer takes the first spot on an empty board."
    (let [test-board (board/create-board)
          player "O"
          expected-board ["O" "" ""
                          ""  "" ""
                          ""  "" ""]]
      (is (= expected-board (make-computer-move test-board player)))))

  (testing "Dumb AI: Given a board with a player on it, take the first available spot."
    (let [test-board (-> (board/create-board)
                         (board/take-spot "X" 0)
                         (board/take-spot "X" 1))
          player "O"
          expected-board ["X" "X" "O"
                          ""  ""  ""
                          ""  ""  ""]]
      (is (= expected-board (make-computer-move test-board player)))))
)

(deftest make-human-move-test
  (testing "Prompt the user to pick a spot"
    (let [test-board (board/create-board)
          player "X"
          result (atom nil)
          mock-writer (fn [msg] (reset! result msg))
          ;; executing for side effects
          _ (make-human-move test-board player mock-writer)]
      (is (= "Pick a spot" @result)))))
