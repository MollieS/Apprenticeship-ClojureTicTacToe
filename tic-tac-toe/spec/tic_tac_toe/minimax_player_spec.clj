(ns tic-tac-toe.minimax-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.minimax-player :refer :all]
            [tic-tac-toe.marks :refer :all]))

(describe "Minimax Player"

          (it "scores 10 plus depth when they win"
              (should= 14 (score [X O nil X O nil X nil nil] X)))

          (it "scores -10 minus depth when opponent wins"
              (should= -14 (score [O X nil O X nil O nil nil] X)))

         (it "scores 0 for a draw"
            (should= 0 (score [X O X O X X O X O] X))
             )

          )
