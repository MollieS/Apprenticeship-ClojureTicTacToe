(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]
            [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.validating-prompt :as prompt]
            [tic-tac-toe.writer :as writer]
            [tic-tac-toe.players :as players]
            [tic-tac-toe.player-options :as player-options] ))

(defn- empty-board[]
  (board/create-empty-board))

(defn- populated-board []
  [X X O nil O X X O O] )

(def fake-players
  {X (fn[board] 2)
   O (fn[board] -1) })

(describe "Tic Tac Toe"
          (with-stubs)
                    (around [it]
                            (with-out-str (it)))

          (it "game is setup for moves to be made"
               (with-redefs [prompt/get-replay-option (stub :replay {:return "No replay"})
                             get-players (stub :get-players {:return fake-players})
                             board/create-empty-board (stub :create-new-board {:return (empty-board)})
                             play-move (stub :play-move {:return "moves are made"})]

                 (play-game)

                 (should-have-invoked :create-new-board {:times 1})
                 (should-have-invoked :get-players {:times 1})
                 (should-have-invoked :play-move {:times 1})))

          (it "game ends with goodbye message"
               (with-redefs [play-game (stub :play-game {:return "game is played"})
                             writer/goodbye-msg (stub :goodbye ) ]
                 (start)

                 (should-have-invoked :play-game {:times 1})
                 (should-have-invoked :goodbye {:times 1})))

          (it "players move updates the board"
              (should= [O nil X nil O X nil nil X]
                       (update-board-with-move fake-players [O nil nil nil O X nil nil X] X)))

          (it "displays board with each move taken and when game is over"
              (with-redefs [writer/display (stub :display {:return "Board is displayed"})
                            prompt/get-replay-option (stub :no-replay {:return "N"})]
                (with-in-str "2\n8\n"
                  (play-move [X nil X O X X O nil O]
                             (players/configure-players player-options/human-human-id))
                  (should-have-invoked :display {:times 3}))))

          (it "displays board when game is won"
              (with-redefs [writer/display (stub :display {:return "Board is displayed"})]
                (announce-win [X O O nil X nil nil X nil])
                (should-have-invoked :display {:times 1})))

          (it "displays board when game is drawn"
              (with-redefs [writer/display (stub :display {:return "Board is displayed"}) ]
                (announce-draw [X O X X X O O X O])
                (should-have-invoked :display {:times 1})))

          (it "checks for a win after each move"
              (with-redefs[board/winning-line? (stub :winning-line? {:return false})
                           prompt/get-replay-option (stub :valid-replay-option {:return "N"})]
                (with-in-str "3\n4\n9\n"
                  (play-move [X O nil nil X O O X nil]
                             (players/configure-players player-options/human-human-id ))

                  (should-have-invoked :winning-line? {:times 4}))))

          (it "checks for a draw after each move until a win takes place"
              (with-redefs [no-free-spaces? (stub :checks-for-draw {:return false})
                            prompt/get-replay-option (stub :valid-replay-option {:return "N"})]

                (with-in-str "3\n4\n9\n"
                  (play-move [X O nil nil X O O X nil]
                             (players/configure-players player-options/human-human-id)))

                (should-have-invoked :checks-for-draw {:times 2})))

          (it "announces win"
              (with-redefs [prompt/get-valid-next-move (stub :next-move {:return 3})
                            prompt/get-replay-option (stub :valid-replay-option {:return "N"})]
                (should-contain "The game was won by X"
                                (with-out-str (play-move [X O nil nil O nil X X O]
                                                         (players/configure-players player-options/human-human-id))))))

          (it "announces draw"
              (with-redefs [prompt/get-valid-next-move (stub :next-move {:return 2})
                            prompt/get-replay-option (stub :valid-replay-option {:return "N"})]
                (should-contain "The game was a draw"
                                (with-out-str (play-move [X O nil O X X O X O]
                                                         (players/configure-players player-options/human-human-id) )))))

          (it "has players take turns until board is full"
              (should-contain "The game was a draw"
                              (with-in-str "1\n2\n3\n4\n5\n7\n6\n9\n8\nN\n"
                                (with-out-str
                                  (play-move (empty-board)
                                             (players/configure-players player-options/human-human-id))))))

          (it "game can be played multiple times"
              (with-redefs [play-move (stub :play-move)
                            empty-board (stub :initial-board {:return populated-board})
                            get-players (stub :players {:return
                                                        (players/configure-players player-options/human-human-id)
                                                        })]

                (with-in-str "Y\nN\n"
                  (with-out-str
                    (play-game))))
              (should-have-invoked :play-move {:times 2})))
