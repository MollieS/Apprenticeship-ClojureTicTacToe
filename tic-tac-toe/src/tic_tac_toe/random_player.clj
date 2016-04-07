(ns tic-tac-toe.random-player
 (:require [tic-tac-toe.board :as board]
           [tic-tac-toe.delayed-player :as delayed-player]))

(defn choose-move [board]
   (rand-nth (board/indicies-of-free-spaces board)))

(def delayed-random-move
  (partial delayed-player/delayed-move 1000 (partial choose-move)))
