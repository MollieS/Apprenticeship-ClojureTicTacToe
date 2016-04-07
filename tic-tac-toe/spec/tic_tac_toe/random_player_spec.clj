(ns tic-tac-toe.random-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.random-player :refer :all]
            [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.board :as board]))

(defn prove-execution[msg]
  (print "Has executed from" msg))

(describe "Random Player"
          (it "Chooses the only free space on the board"
              (should= 7
                       (choose-move [X O X X X O O nil O])))

          (it "Chooses a free space from a partially populated board"
              (should-contain (choose-move [X O nil nil nil X O nil X])
                              '(2 3 4 7)))

          (it "Chooses a free space on an empty board"
              (should-contain (choose-move (vec (repeat 9 nil)))
                              (range 0 9)))

          (it "Executes another function when making a delayed move"
              (binding [sleep prove-execution]
                (should= "Has executed from delayed-move test"
                         (with-out-str
                           (delayed-move "delayed-move test" [X O X X X O O nil O])))))

          (it "Chooses valid move when delay mechanism is in place"
              (binding [sleep (fn [nothing-happens] nothing-happens)]
                (should= 7
                         (delayed-move "delayed-move test" [X O X X X O O nil O])))))
