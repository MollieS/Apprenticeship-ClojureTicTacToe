(ns tic-tac-toe.minimax-player
  (:require [tic-tac-toe.minimax-player :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.marks :as marks]))


(defn- minimax-wins? [board minimax-symbol]
  (= (board/winning-symbol board) minimax-symbol))


(defn score [board minimax-symbol]
  (if (board/winning-line? board)
    (do
      (cond
        (minimax-wins? board minimax-symbol) (+ 10  (count (board/indicies-of-free-spaces board)))
        :else
        (- -10 (count (board/indicies-of-free-spaces board)))))))
