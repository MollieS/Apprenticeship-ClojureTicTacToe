(ns tic-tac-toe.minimax-player
  (:require [tic-tac-toe.minimax-player :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.marks :as marks]))

(defn- minimax-wins? [board minimax-symbol]
  (= (board/winning-symbol board) minimax-symbol))

(defn- score-opponent-win [board remaining-depth]
  (- -10 remaining-depth))

(defn- score-minimax-win [board remaining-depth]
  (+ 10 remaining-depth))

(defn- draw? [board]
  (not (board/free-spaces? board)))

(defn- score-for-win [board minimax-symbol remaining-depth]
  (do
    (cond
      (minimax-wins? board minimax-symbol) (score-minimax-win board remaining-depth)
      :else
      (score-opponent-win board remaining-depth))))

(defn score [board minimax-symbol remaining-depth]
  (cond
    (board/winning-line? board) (score-for-win board minimax-symbol remaining-depth)
    (draw? board) 0))

(defn- game-over? [board]
  (or (board/winning-line? board)
      (draw? board)))

(defn- calculate-initial-score [board minimax-symbol]
  (if (= minimax-symbol (marks/next-mark board))
    -100
    100
    )
  )

(defn- minimax [board minimax-symbol depth]
  (let [best-score (calculate-initial-score board minimax-symbol)]

    (if (or (game-over? board) (= 0 depth))
    (score board minimax-symbol depth))

    )
  )

(defn choose-move [board]
  (let [minimax-symbol (marks/next-mark board)
        [[best-score best-move]] (minimax board minimax-symbol (count (board/indicies-of-free-spaces board)))]
    best-move))
