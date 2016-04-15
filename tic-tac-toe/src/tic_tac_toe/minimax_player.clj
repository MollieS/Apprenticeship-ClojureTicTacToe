(ns tic-tac-toe.minimax-player
  (:require [tic-tac-toe.minimax-player :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.marks :as marks]
            [tic-tac-toe.delayed-player :as delayed-player]))

(def draw-score 0)
(def max-score 10)
(def min-score -10)
(def initial-max-score -100)
(def initial-min-score 100)
(def position-placeholder -1)
(def initial-alpha -100)
(def initial-beta 100)

(defn- calculate-initial-score [is-max-player]
  (if is-max-player
    [position-placeholder initial-max-score]
    [position-placeholder initial-min-score]))

(defn- max-player-winning-score [depth]
  (+ max-score depth))

(defn- min-player-winning-score [depth]
  (- min-score depth))

(defn- get-winning-score [max-player-symbol board depth]
  (if (= max-player-symbol (board/winning-symbol board))
    (max-player-winning-score depth)
    (min-player-winning-score depth)))

(defn- calculate-game-over-score [board max-player-symbol depth]
  (if (board/winning-line? board)
    [position-placeholder (get-winning-score max-player-symbol board depth)]
    [position-placeholder draw-score]))

(defn- find-max [is-max-player best-position valued-position index-of-move ]
  (if (< (second best-position) (second valued-position))
    (vector index-of-move (second valued-position))
    best-position))

(defn- find-min [is-max-player best-position valued-position index-of-move]
  (if (> (second best-position) (second valued-position))
    (vector index-of-move (second valued-position))
    best-position))

(defn- get-players-best-position [is-max-player best-position valued-position index-of-move]
  (if is-max-player
    (find-max is-max-player best-position valued-position index-of-move)
    (find-min is-max-player best-position valued-position index-of-move)))

(defn- game-over? [depth board]
  (or (= depth 0)
      (board/winning-line? board)))

(defn- no-free-spaces [remaining-spaces]
  (= 0 (count remaining-spaces)))

(defn- recalculate-alpha [is-max-player best-score alpha]
  (if is-max-player
    (max (second best-score) alpha)
    alpha))

(defn- recalculate-beta [is-max-player best-score beta]
  (if is-max-player
    beta
    (min (second best-score) beta)))

(defn- prune? [alpha beta]
  (>= alpha beta))

(defn- stop-exploration-of-branches? [remaining-spaces alpha beta]
  (or
    (prune? alpha beta)
    (no-free-spaces remaining-spaces)))

(defn minimax [board depth is-max-player max-player-symbol alpha beta]
  (let [initial-score (calculate-initial-score is-max-player)]

    (if (game-over? depth board)
      (calculate-game-over-score board max-player-symbol depth)
      (do
        (loop [[first-free-slot & rest] (board/indicies-of-free-spaces board)
               best-position initial-score
               alpha alpha
               beta beta]

          (let [updated-board (board/place-mark board (marks/next-mark board) first-free-slot)
                position (minimax
                           updated-board
                           (dec depth)
                           (not is-max-player)
                           max-player-symbol
                           alpha
                           beta)]

            (let [latest-best-score (get-players-best-position is-max-player best-position position first-free-slot)
                  new-alpha (recalculate-alpha is-max-player latest-best-score alpha)
                  new-beta (recalculate-beta is-max-player latest-best-score beta)]
              (if (stop-exploration-of-branches? rest new-alpha new-beta)
                latest-best-score
                (recur rest latest-best-score new-alpha new-beta)))))))))

(defn choose-move [board]
  (let [is-max-player true
        max-player-symbol (marks/next-mark board)
        [best-position best-score] (minimax
                                     board
                                     (count (board/indicies-of-free-spaces board))
                                     is-max-player
                                     max-player-symbol
                                     initial-alpha
                                     initial-beta)]
    best-position))

(def delayed-unbeatable-move
  (partial delayed-player/delayed-move 1000 (partial choose-move)))
