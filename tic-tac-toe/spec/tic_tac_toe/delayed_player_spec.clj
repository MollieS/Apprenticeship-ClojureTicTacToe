(ns tic-tac-toe.random-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.random-player :refer :all]
            [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.delayed-player :as delayed-player]))

(def fake-player 2)

(defn prove-execution[msg]
  (print "Has executed from" msg))

(describe "Delayed Player"

          (it "Executes another function when making a delayed move"
              (binding [delayed-player/sleep prove-execution]
                (should= "Has executed from delayed-move test"
                         (with-out-str
                           (delayed-move "delayed-move test" fake-player [X O X X X O O nil O])))))

          (it "Provides next move"
              (binding [delayed-player/sleep (fn [nothing-happens] nothing-happens)]
                (should= 2
                         (delayed-move "delayed-move test" fake-player [X O X X X O O nil O])))))
