(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]
            [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.validating-prompt :as prompt]
            [tic-tac-toe.writer :as writer]
            [tic-tac-toe.player :as player]))

(defn- empty-board[]
  (vec (repeat 9 nil)))

(describe "Tic Tac Toe"
          (with-stubs)
          (around [it]
                  (with-out-str (it)))

          (it "game determines player options"
              (with-redefs [prompt/get-valid-player-option (stub :valid-player-option {:return 1})
                            play-move (stub :play-move {:return "whole game is stubbed"})]
                (start)
                (should-have-invoked :valid-player-option {:times 1})
                ))

          (it "game begins with displaying an empty board"
              (with-redefs [prompt/get-valid-player-option (stub :valid-player-option {:return 1})
                            board/create-empty-board (stub :create {:return (empty-board)})
                            play-move (stub :play-move {:return "whole game is stubbed"})
                            writer/display (stub :display {:return "Board is displayed"}) ]
                (start)

                (should-have-invoked :create {:times 1})
                (should-have-invoked :play-move {:times 1})
                (should-have-invoked :display {:times 1})))

          (it "takes the next move from the player"
              (with-redefs [player/choose-move (stub :player {:return 2})]
                (play-move [O nil nil nil O X nil nil X])
                (should-have-invoked :player {:times 1})
                )
              )

          (it "displays board with each move"
              (with-redefs [writer/display (stub :display {:return "Board is displayed"})]
                ( with-in-str "3\n4\n9\n"
                  (play-move [X O nil nil X O O X nil]))
                (should-have-invoked :display {:times 3})
                )
              )

          (it "checks for a win after each move"
              (with-redefs [board/winning-line?(stub :winning-line? {:return false})]
                ( with-in-str "3\n4\n9\n"
                  (play-move [X O nil nil X O O X nil]))
                (should-have-invoked :winning-line? {:times 3})
                )
              )

          (it "checks for a draw after each move until a win takes place"
              (with-redefs [board/free-spaces?(stub :free-spaces? {:return true})]
                ( with-in-str "3\n4\n9\n"
                  (play-move [X O nil nil X O O X nil]))
                (should-have-invoked :free-spaces? {:times 2})
                )
              )

          (it "announces win"
              (with-redefs [prompt/get-valid-next-move (stub :next-move {:return 3}) ]
                (should-contain "The game was won by X"
                                (with-out-str (play-move [X O nil nil O nil X X O])))))

          (it "announces draw"
              (with-redefs [prompt/get-valid-next-move (stub :next-move {:return 2})]
                (should-contain "The game was a draw"
                                (with-out-str (play-move [X O nil O X X O X O])))))

          (it "has players take turns until board is full"
              (should-contain "The game was a draw"
                              (with-in-str "1\n2\n3\n4\n5\n7\n6\n9\n8\n"
                                (with-out-str
                                  (play-move (empty-board)))))))
