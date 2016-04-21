(ns ttt.player-test
  (:require [clojure.test :refer :all]
            [ttt.test-helper :refer :all]
            [ttt.player :refer :all]))

(deftest other-player-test
  (testing "Given player-one, return player-two."
    (is (= player-two (other-player player-one))))

  (testing "Given player-two, return player-one."
    (is (= player-one (other-player player-two))))
)
