(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]
            [tic-tac-toe.marks :refer :all]))

(defn- empty-board[]
  (vec (repeat 9 nil)))

(defn- partially-populated-board[]
  [nil X O nil nil X nil O X])

(describe "Board"

          (it "created with given configuration"
              (should= (partially-populated-board)
                       (create [nil X O nil nil X nil O X])))


          (it "updates with players mark at given index"
              (should= [nil nil nil nil nil nil nil nil X]
                       (place-mark (empty-board) X 8)))


          (it "gets the players mark at a given index"
              (should= X
                       (get-mark-at-index (partially-populated-board) 5)))

          (it "gets rows for display"
              (should= [[nil X O][nil nil X][nil O X]]
                       (get-rows (partially-populated-board)))))



