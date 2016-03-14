(ns tic-tac-toe.ttt-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.ttt :refer :all]
            [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.board :refer :all]
            [tic-tac-toe.prompt :refer :all]
            [tic-tac-toe.writer :refer :all]
            [tic-tac-toe.reader :refer :all]))

(defn- empty-board[]
  (vec (repeat 9 nil)))

(describe "Tic Tac Toe"
          (with-stubs)
          (around [it]
                  (with-out-str (it)))

          (it "game starts by displaying an empty board"
              (with-redefs [create (stub :create {:return (empty-board)})
                            play (stub :play {:return "whole game is stubbed"})
                            display (stub :display {:return "Board is displayed"}) ]
                (start)

                (should-have-invoked :create {:times 1})
                (should-have-invoked :play {:times 1})
                (should-have-invoked :display {:times 1})))

          (it "displays board with each move"
              (with-redefs [display (stub :display {:return "Board is displayed"})]
                ( with-in-str "3\n4\n9\n"
                  (play [X O nil nil X O O X nil]))
                (should-have-invoked :display {:times 3})
                )
              )

          (it "Checks for a win after each move"
              (with-redefs [winning-line?(stub :winning-line? {:return false})]
                ( with-in-str "3\n4\n9\n"
                  (play [X O nil nil X O O X nil]))
                (should-have-invoked :winning-line? {:times 3})
                )
              )

          (it "Checks for a draw after each move until a win takes place"
              (with-redefs [free-spaces?(stub :free-spaces? {:return true})]
                ( with-in-str "3\n4\n9\n"
                  (play [X O nil nil X O O X nil]))
                (should-have-invoked :free-spaces? {:times 2})
                )
              )

          (it "Announces win"
              (with-redefs [valid-next-move (stub :next-move {:return 3}) ]
                (should-contain "The game was won by X"
                                (with-out-str (play [X O nil nil O nil X X O])))))

          (it "Announces draw"
              (with-redefs [valid-next-move (stub :next-move {:return 2})]
                (should-contain "The game was a draw"
                                (with-out-str (play [X O nil O X X O X O])))))

          (it "has players take turns until board is full"
              (should-contain "The game was a draw"
                              (with-in-str "1\n2\n3\n4\n5\n7\n6\n9\n8\n"
                                (with-out-str
                                  (play (empty-board)))))))