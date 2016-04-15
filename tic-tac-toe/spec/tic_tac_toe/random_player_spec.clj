(ns tic-tac-toe.random-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.random-player :refer :all]
            [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.board :as board]))

(describe "Random Player"
          (it "Chooses the only free space on the board"
              (should= 7
                       (choose-move [X O X X X O O nil O])))

          (it "Chooses a free space from a partially populated board"
              (should-contain (choose-move [X O nil nil nil X O nil X])
                              '(2 3 4 7)))

          (it "Chooses a free space on an empty board"
              (should-contain (choose-move (vec (repeat 9 nil)))
                              (range 0 9))))
