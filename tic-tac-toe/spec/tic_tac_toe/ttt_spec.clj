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
                  (with-out-str (it))
                  )

          (it "creates empty board at the start of the game"
              (with-redefs [play (fn [board] "Anything returned")]
                (should-invoke create {:times 1}
                               (should-invoke display {:times 1}
                                              (start)))))

          (it "displays board with players move"
              (with-redefs [display (stub :display)
                            valid-next-move (stub :next-move {:return 3})
                            ]
                (let [game-result (play [X O nil nil X O O X O])]
                  (should-have-invoked :display {:times 1})
                  (should-have-invoked :next-move {:times 1})
                  )
                ))

          (it "Announces win"
              (with-redefs [valid-next-move (stub :next-move {:return 3}) ]
                (should-contain "The game was won by X"
                                (with-out-str (play [X O nil nil O nil X X O]))
                                )))

          (it "Announces draw"
              (with-redefs [valid-next-move (stub :next-move {:return 2})]
                (should-contain "The game was a draw"
                                (with-out-str (play [X O nil O X X O X O]))
                                )))

          )
