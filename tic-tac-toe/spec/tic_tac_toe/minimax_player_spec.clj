(ns tic-tac-toe.minimax-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.minimax-player :refer :all]
            [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.board :as board]
            [clj-time.core :as date-time]))

(def win-board [X O nil X O nil X nil nil])
(def draw-board [O X O X O O X O X])

(defn- time-taken [starting-time finish-time]
  (date-time/in-millis (date-time/interval starting-time finish-time)))

(describe "Minimax Player"
          (it "takes winning move on top row"
              (should= 0
                       (choose-move [nil X X O O nil nil nil nil])))

          (it "takes winning move on middle row"
              (should= 5
                       (choose-move [nil nil nil X X nil O O nil])))

          (it "takes winning move on bottow row"
              (should= 8
                       (choose-move [nil O O nil nil nil X X nil])))

          (it "takes winning move on left column"
              (should= 3
                       (choose-move [X nil O nil O nil X nil nil ])))

          (it "takes winning move on middle column"
              (should= 4
                       (choose-move [nil O nil X nil X nil nil O])))

          (it "takes wininng move on right column"
              (should= 2
                       (choose-move [nil nil nil nil O X nil O X])))

          (it "takes winning move on first diagonal"
              (should= 4
                       (choose-move [X nil O nil nil O nil nil X])))

          (it "takes winning move on second diagonal"
              (should= 2
                       (choose-move [nil nil nil nil X O X nil O])))

          (it "blocks opponent on top row"
              (should= 1
                       (choose-move [O nil O X nil nil nil nil X])))

          (it "blocks opponent on middle row"
              (should= 3
                       (choose-move [nil nil X nil O O nil X nil])))

          (it "blocks opponent on bottom row"
              (should= 7
                       (choose-move [nil nil X nil X nil O nil O])))

          (it "blocks opponent on left column"
              (should= 3
                       (choose-move [O X nil nil nil X O nil nil])))

          (it "blocks opponent in middle column"
              (should= 7
                       (choose-move [nil O nil X O nil nil nil X])))

          (it "blocks opponent in right column"
              (should= 5
                       (choose-move [nil X O nil nil nil X nil O])))

          (it "blocks opponent in first diagonal"
              (should= 8
                       (choose-move [O nil X nil O nil nil X nil])))

          (it "blocks opponent in second diagonal"
              (should= 4
                       (choose-move [nil nil O nil nil X O nil X])))

         (it "takes middle slot when top left is occupied"
          (should= 4
                   (choose-move [X nil nil nil nil nil nil nil nil])))

         (it "does not allow a fork to form"
            (should= 1
                     (choose-move [X nil nil nil O nil nil nil X])))

          (it "takes first move in under one second"
              (let [starting-time (date-time/now)]
                (choose-move (board/create-empty-board))
                (let [finish-time (date-time/now)]
                  (should= true
                           (< (time-taken starting-time finish-time) 1000 ) )))))
