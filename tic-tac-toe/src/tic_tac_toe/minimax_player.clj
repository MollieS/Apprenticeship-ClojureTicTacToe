(ns tic-tac-toe.minimax-player
  (:require [tic-tac-toe.minimax-player :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.marks :as marks]))

(defn- minimax-wins? [board minimax-symbol]
  (= (board/winning-symbol board) minimax-symbol))

(defn- score-opponent-win [board]
  (- -10 (count (board/indicies-of-free-spaces board))))

(defn- score-minimax-win [board]
  (+ 10  (count (board/indicies-of-free-spaces board))))

(defn- draw? [board]
  (not (board/free-spaces? board)))

(defn- score-for-win [board minimax-symbol]
  (do
    (cond
      (minimax-wins? board minimax-symbol) (score-minimax-win board)
      :else
      (score-opponent-win board))))

(defn score [board minimax-symbol]
  (cond
    (board/winning-line? board) (score-for-win board minimax-symbol)
    (draw? board) 0))

(defn- game-over? [board]
  (or (board/winning-line? board)
            (draw? board)))

(defn- determine-move [board minimax-symbol]
    (if (game-over? board)
      (score board minimax-symbol))
  )

(defn choose-move [board]
  (let [minimax-symbol (marks/next-mark board)]
   (determine-move board minimax-symbol))
  )
