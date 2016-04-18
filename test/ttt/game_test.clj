(ns ttt.game-test
  (require [clojure.test :refer :all]
           [ttt.test-helper :refer :all]
           [ttt.board :as board]
           [ttt.game :refer :all]))


(deftest make-computer-move-test
  (testing "Dumb AI: Computer takes the first spot on an empty board."
    (let [test-board (board/create-board)
          player O
          expected-board [O E E
                          E E E
                          E E E]]
      (is (= expected-board (make-move test-board player)))))

  (testing "Dumb AI: Given a board with a player on it, take the first available spot."
    (let [test-board (-> (board/create-board)
                         (board/take-spot X 0)
                         (board/take-spot X 1))
          player O
          expected-board [X X O
                          E E E
                          E E E]]
      (is (= expected-board (make-move test-board player)))))
)

(deftest make-human-move-test
  (testing "Prompt the user to pick a spot."
    (let [test-board (board/create-board)
          player X
          spot "4"
          result (atom nil)
          mock-reader (fn [] spot)
          mock-writer (fn [msg] (reset! result msg))
          ;; executing for side effects
          _ (make-move test-board player mock-reader mock-writer)]
      (is (= "Pick a spot" @result))))

  (testing "Get the user's spot by reading input."
    (let [test-board (board/create-board)
          player X
          spot "4"
          result (atom nil)
          mock-writer (fn [msg] nil)
          mock-reader (fn [] (reset! result spot))
          ;; executing for side effects
          _ (make-move test-board player mock-reader mock-writer)]
      (is (= spot @result))))

  (testing "Given the user picks an invalid spot, then notify and prompt the user again."
    (let [test-board (board/create-board)
          player X
          ;; the read-queue must contain a valid spot, or the test will
          ;; fail due to recursion limits.
          read-queue (atom '("100" "4"))
          mock-reader (fn []
                        (let [result (peek @read-queue)]
                          (when result
                            (reset! read-queue (pop @read-queue)))
                          result))
          write-result (atom [])
          mock-writer (fn [msg] (reset! write-result (conj @write-result msg)))
          ;; executing for side effects
          _ (make-move test-board player mock-reader mock-writer)]
      (is (some #{"Invalid spot"} @write-result))
      (is (= 2 (count (filter #{"Pick a spot"} @write-result))))))

  (testing "Given the user picks a valid spot, then take the spot on the board."
    (let [test-board (board/create-board)
          player X
          spot "4"
          mock-reader (fn [] spot)
          mock-writer (fn [msg] nil)
          expected-board [E E E
                          E X E
                          E E E]
          actual-board (make-move test-board player mock-reader mock-writer)]
      (is (= expected-board actual-board))))
)

(deftest get-winner-test
  (testing "Given a board where player-one wins, return player-one."
    (let [test-board [X X X
                      E E E
                      E E E]
          players [X O]]
      (is (= X (get-winner test-board players)))))

  (testing "Given a board where player-two wins, return player-two"
    (let [test-board [O O O
                      E E E
                      E E E]
          players [X O]]
      (is (= O (get-winner test-board players)))))

  (testing "Given a board where there is no winner, return nil."
    (let [test-board [X O X
                      O X O
                      O X O]
          players [X O]]
      (is (= nil (get-winner test-board players)))))
)

(deftest prompt-test
  (testing "Prompt: Pick spot."
    (is (= "Pick a spot" (prompt :pick-spot))))

  (testing "Prompt: Invalid spot."
    (is (= "Invalid spot" (prompt :invalid-spot))))

  (testing "Prompt: Invalid message-key returns nil."
    (is (= nil (prompt :bogus-message-key)))))
