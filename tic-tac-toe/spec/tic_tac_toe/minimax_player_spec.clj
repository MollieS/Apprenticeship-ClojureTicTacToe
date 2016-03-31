(ns tic-tac-toe.minimax-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.minimax-player :refer :all]
            [tic-tac-toe.marks :refer :all]))

(def win-board [X O nil X O nil X nil nil])
(def draw-board [O X O X O O X O X])

(describe "Minimax Player"

          (it "scores 10 plus depth when they win"
              (should= [-1 14] (calculate-game-over-score win-board X 4)))

          (it "scores -10 minus depth when opponent wins"
              (should= [-1 -14] (calculate-game-over-score [O X nil O X nil O nil nil] X 4)))

          (it "scores 0 for a draw"
              (should= [-1 0] (calculate-game-over-score draw-board X 0)))

          (it "calculates score when there are no spaces left on the board"
              (should-invoke calculate-game-over-score {:times 1}
                             (choose-move draw-board)))

          (it "calculates score when there is a win on the board"
              (should-invoke calculate-game-over-score {:times 1}
                            (choose-move win-board)))

          (it "takes winning move on top row"
              (should= 0
                       (choose-move [nil X X O O nil nil nil nil])))

          (it "takes winning move on middle row"
              (should= 5
                       (choose-move [nil nil nil X X nil O O nil])))

          (it "takes winning move on bottow row"
              (should= 8
                       (choose-move [nil O O nil nil nil X X nil])))

          (it "takes winning move on left column"
              (should= 3
                       (choose-move [X nil O nil O nil X nil nil ])))

          (it "takes winning move on middle column"
              (should= 4
                       (choose-move [nil O nil X nil X nil nil O])))

          (it "takes wininng move on right column"
              (should= 2
                       (choose-move [nil nil nil nil O X nil O X])))

          (it "takes winning move on first diagonal"
              (should= 4
                       (choose-move [X nil O nil nil O nil nil X])))

          (it "takes winning move on second diagonal"
              (should= 2
                       (choose-move [nil nil nil nil X O X nil O])))

          (it "blocks opponent on top row"
              (should= 1
                       (choose-move [O nil O X nil nil nil nil X])))

          (it "blocks opponent on middle row"
              (should= 3
                       (choose-move [nil nil X nil O O nil X nil])))

          (it "blocks opponent on bottom row"
              (should= 7
              (choose-move [nil nil X nil X nil O nil O])))

          (it "blocks opponent on left column"
              (should= 3
                       (choose-move [O X nil nil nil X O nil nil])))

          (it "blocks opponent in middle column"
              (should= 7
                       (choose-move [nil O nil X O nil nil nil X])))

          (it "blocks opponent in right column"
              (should= 5
                       (choose-move [nil X O nil nil nil X nil O])))

          (it "blocks opponent in first diagonal"
              (should= 8
                       (choose-move [O nil X nil O nil nil X nil])))

          (it "blocks opponent in second diagonal"
              (should= 4
                       (choose-move [nil nil O nil nil X O nil X])))

      ;   (it "blocks fork"
      ;      (should= 4
      ;               (choose-move [X nil nil nil nil nil nil nil nil]))
      ;       )
          )
