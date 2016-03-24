(ns tic-tac-toe.marks-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.marks :refer :all]))

(describe "Marks"

          (it "X starts the game"
              (should= X
                       (next-mark [nil nil nil nil nil nil nil nil nil])))

          (it "calculates next symbol is O"
              (should= O
                       (next-mark [X O nil X nil nil nil nil nil]))
              )

          (it "calculates next symbol is X"
              (should= X
                       (next-mark [X O nil nil nil nil nil nil nil])))

          (it "calculates O's opponent"
              (should= X
                       (opponent O)))

          (it "calculates X's opponent"
              (should= O
                       (opponent X))))
