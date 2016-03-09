(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]
            [tic-tac-toe.marks :refer :all]))

(defn- empty-board[]
  (vec (repeat 9 nil)))

(describe "Board"

(it "created with given configuration"
    (should=[X O nil nil nil nil nil nil nil]
               (create [X O nil nil nil nil nil nil nil])))


          (it "updates with players mark at given index"
              (should= [nil nil nil nil nil nil nil nil X]
                       (place-mark (empty-board) X 8))))
