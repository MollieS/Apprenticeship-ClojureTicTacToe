(ns tic-tac-toe.random-player
 (:require [tic-tac-toe.board :as board]))

(def ^:dynamic sleep #(Thread/sleep %))

(defn choose-move [board]
   (rand-nth (board/indicies-of-free-spaces board)))

(defn delayed-move [interval board]
 (sleep interval)
 (choose-move board))

(def delayed-random-move
  (partial delayed-move 1000))
