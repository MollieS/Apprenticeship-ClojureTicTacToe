(ns tic-tac-toe.ttt
  (:require [tic-tac-toe.ttt :refer :all]
            [tic-tac-toe.prompt :refer :all]
            [tic-tac-toe.writer :refer :all]
            [tic-tac-toe.board :refer :all]
            [tic-tac-toe.marks :refer :all]))


(defn play[]
 (display (create (vec (repeat 9 nil)))))
