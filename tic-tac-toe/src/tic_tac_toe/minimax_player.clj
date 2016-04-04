(ns tic-tac-toe.minimax-player
  (:require [tic-tac-toe.minimax-player :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.marks :as marks]))

(declare minimax)

(def draw-score 0)
(def max-score 10)
(def min-score -10)
(def initial-max-score -100)
(def initial-min-score 100)
(def position-placeholder -1)

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

(defn calculate-game-over-score [board max-player-symbol depth]
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

(defn- calculate-best-score [free-slots board best-position depth is-max-player max-player-symbol]
  (loop [[first-free-slot & rest] free-slots
         best-position best-position]

    (let [updated-board (board/place-mark board (marks/next-mark board) first-free-slot)
          position (minimax
                     updated-board
                     (dec depth)
                     (not is-max-player)
                     max-player-symbol)]

      (let [latest-best-score (get-players-best-position is-max-player best-position position first-free-slot)]
        (if (no-free-spaces rest)
          latest-best-score
          (recur rest latest-best-score))))))

(defn minimax [board depth is-max-player max-player-symbol]
  (let [initial-score (calculate-initial-score is-max-player)]

    (if (game-over? depth board)
      (calculate-game-over-score board max-player-symbol depth)
      (calculate-best-score (board/indicies-of-free-spaces board) board initial-score depth is-max-player max-player-symbol))))

(defn choose-move [board]
  (let [is-max-player true
        max-player-symbol (marks/next-mark board)
        [best-position best-score] (minimax
                                     board
                                     (count (board/indicies-of-free-spaces board))
                                     is-max-player
                                     max-player-symbol)]
    best-position))
