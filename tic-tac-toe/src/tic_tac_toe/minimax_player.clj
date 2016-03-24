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

(defn- game-over? [board depth]
  (or (board/winning-line? board)
      (draw? board)
      (= 0 depth)))

(defn- calculate-initial-score [board minimax-symbol]
  (if (= minimax-symbol (marks/next-mark board))
    -100
    100
    )
  )

(defn- get-best-position [is-minimax-player [best-score best-move] as best-pos [current-score current-move] as curr-pos current-position]
  (if is-minimax-player
    (if (< best-score current-score)
      [current-score current-position]
      best-pos)
    (if (> best-score current-score)
      [current-score current-position]
      best-pos)
    )
  )

; have tried to mimic my java one without pruning.
; need to walk through this algorithm with the test cases to see what is wrong
(defn- minimax [board minimax-symbol depth]
  (let [init-best-position (calculate-initial-score board minimax-symbol)]

    (if (game-over? board depth)
      (score board minimax-symbol depth)

    (let [ [h & t] (board/indicies-of-free-spaces board)
          next-symbol (marks/next-mark board)
          updated-board (board/place-mark board next-symbol (Integer/parseInt h))
          position (minimax updated-board minimax-symbol (- depth 1) )]
      (println "next symbol is " next-symbol)
      (println "updated board is " updated-board)
      (println "position " position)
      (get-best-position (= minimax-symbol next-symbol) init-best-position position (Integer/parseInt h))
      ))
    )
  )

(defn choose-move [board]
  (let [ minimax-symbol (marks/next-mark board)
        [[best-score best-move]] (minimax board minimax-symbol (count (board/indicies-of-free-spaces board)))]
    best-move))
