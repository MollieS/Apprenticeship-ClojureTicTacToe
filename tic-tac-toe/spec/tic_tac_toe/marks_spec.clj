(ns tic-tac-toe.marks-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.marks :refer :all]))

(describe "Marks"

          (it "X starts the game"
              (should= X
                       (next-mark [nil nil nil nil nil nil nil nil nil])))

          (it "calculates next symbol"
              (should= O
                       (next-mark [X O nil X nil nil nil nil nil]))
              )
          )


