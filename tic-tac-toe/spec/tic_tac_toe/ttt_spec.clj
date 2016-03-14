(ns tic-tac-toe.ttt-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.ttt :refer :all]
            [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.board :refer :all]
            [tic-tac-toe.prompt :refer :all]
            [tic-tac-toe.writer :refer :all]
            [tic-tac-toe.reader :refer :all]))

(describe "Tic Tac Toe"
          (with-stubs)
          (around [it]
                  (with-redefs [valid-next-move (fn [board] 4)]
                    (with-out-str (it))))

          (it "Creates empty board at the start of the game"
              (with-redefs [play (fn [board] "Game over")]
                (should-invoke create {:times 1}
                               (start))))

          (it "displays board with players move"
              (should-invoke display {:times 2}
                             (should-invoke place-mark {:times 1}
                                            (should-invoke valid-next-move {:times 1}
                                            (play [X O nil nil X O O X O O])))))

          )
