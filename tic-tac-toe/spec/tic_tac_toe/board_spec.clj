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
                       (get-rows (partially-populated-board))))

          (it "does not see three nils as a win"
              (should= false
                       (winning-line? (empty-board))))

          (it "has a win in the top row"
              (should= true
                       (winning-line? [X X X nil O nil O nil nil])))


          (it "has win in the middle row"
              (should= true
                       (winning-line? [nil nil nil X X X O O nil])))

          (it "has win in the bottom row"
              (should= true
                       (winning-line? [nil nil nil X X nil O O O])))


          (it "has win in the first column"
              (should= true
                       (winning-line? [O X nil O nil nil O nil nil])))

          (it "has win in the middle column"
              (should= true
                       (winning-line? [O X nil nil X O nil X nil])))

          (it "has win in the right column"
              (should= true
                       (winning-line? [nil nil X O O X nil nil X])))

          (it "has a win in first diagonal"
              (should= true
                       (winning-line? [O nil nil X O nil X nil O])))

          (it "has a win in second diagonal"
              (should= true
                       (winning-line? [nil nil X O X nil X nil O])))

          (it "has a winning left row of X"
              (should= X
                       (winning-symbol [X X X O O nil X nil O])))

          (it "has a winning middle row of X"
              (should= X
                       (winning-symbol [nil nil nil X X X O nil O])))

          (it "has a winning right row of O"
              (should= O
                       (winning-symbol [nil nil X X nil X O O O])))

          (it "has a winning left column of X"
              (should= X
                       (winning-symbol [X nil nil X O O X nil nil])))

          (it "has a winning middle column of X"
              (should= X
                       (winning-symbol [nil X nil nil X O O X nil])))

          (it "has a winning right column of O"
              (should= O
                       (winning-symbol [nil nil O X X O nil nil O])))
          )
