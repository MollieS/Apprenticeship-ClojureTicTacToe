(ns tic-tac-toe.minimax-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.minimax-player :refer :all]
            [tic-tac-toe.marks :refer :all]))

(def win-board [X O nil X O nil X nil nil])
(def draw-board [X O X O X X O X O])

(describe "Minimax Player"

          (it "scores 10 plus depth when they win"
              (should= 14 (score win-board X)))

          (it "scores -10 minus depth when opponent wins"
              (should= -14 (score [O X nil O X nil O nil nil] X)))

          (it "scores 0 for a draw"
              (should= 0 (score draw-board X))
              )

          (it "calculates score when there are no spaces left on the board"
              (should-invoke score {:times 1}
                             (choose-move draw-board)))

          (it "calculates score when there is a win on the board"
              (should-invoke score {:times 1}
                             (choose-move win-board)))
          )
