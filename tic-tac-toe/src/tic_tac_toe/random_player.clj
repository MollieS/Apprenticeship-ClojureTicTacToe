(ns tic-tac-toe.random-player
 (:require [tic-tac-toe.board :as board]
  ))

(defn choose-move [board]
  (rand-nth (board/indicies-of-free-spaces board)))
