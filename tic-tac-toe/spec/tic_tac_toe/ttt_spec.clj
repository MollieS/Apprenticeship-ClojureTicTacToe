(ns tic-tac-toe.ttt-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.ttt :refer :all]
            [tic-tac-toe.board :refer :all]
            [tic-tac-toe.prompt :refer :all]
            [tic-tac-toe.writer :refer :all]))

(describe "Tic Tac Toe"



          (it "displays board"
              (should-invoke create {:times 1}
                             (should-invoke display {:times 1}
                             (play))))
          )
