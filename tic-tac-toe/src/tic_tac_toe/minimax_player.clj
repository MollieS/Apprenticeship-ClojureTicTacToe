(ns tic-tac-toe.minimax-player
  (:require [tic-tac-toe.minimax-player :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.marks :as marks]))

(declare minimax)

(def DRAW-SCORE 0)
(def MAX-SCORE 10)
(def MIN-SCORE -10)
(def INITIAL-MAX-SCORE -100)
(def INITIAL-MIN-SCORE 100)
(def DUMMY-POSITION -1)

(defn- calculate-initial-score [is-max-player]
  (if is-max-player
    [DUMMY-POSITION INITIAL-MAX-SCORE]
    [DUMMY-POSITION INITIAL-MIN-SCORE]))

(defn- max-player-winning-score [depth]
  (+ MAX-SCORE depth))

(defn- min-player-winning-score [depth]
  (- MIN-SCORE depth))

(defn- get-winning-score [max-player-symbol board depth]
  (if (= max-player-symbol (board/winning-symbol board))
    (max-player-winning-score depth)
    (min-player-winning-score depth)))

(defn calculate-game-over-score [board max-player-symbol depth]
  (if (board/winning-line? board)
    [DUMMY-POSITION (get-winning-score max-player-symbol board depth)]
    [DUMMY-POSITION DRAW-SCORE]))

(defn- find-position [best-score [head & tail]]
  (let [[position score] head]
    (if (= score best-score)
      position
      (find-position best-score tail))))

(defn- find-max [is-max-player best-position valued-position index-of-move ]
  (println "finding min")
  (println "best position is " best-position)
  (println "valued position is" valued-position)

  (if (< (second best-position) (second valued-position))
    (vector index-of-move (second valued-position))
    best-position))

(defn- find-min [is-max-player best-position valued-position index-of-move]
  (println "finding max")
  (println "best position is " best-position)
  (println "valued position is" valued-position)

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

(defn- calculate-best-score [free-slots  board best-position depth is-max-player max-player-symbol]
  (loop [[head & tail] free-slots
    board board
    best-position best-position
    depth depth
    is-max-player is-max-player]

  (let [ updated-board (board/place-mark board (marks/next-mark board) head)
        position (minimax
                   updated-board
                   (dec depth)
                   (not is-max-player)
                   max-player-symbol)]

    (let [latest-best-score (get-players-best-position is-max-player best-position position head)]
      (if (= 0  (count tail))
        latest-best-score
        (recur tail board latest-best-score
                              depth
                              is-max-player )
        ))
    )))

(defn minimax [board depth is-max-player max-player-symbol]
  (let [initial-score (calculate-initial-score is-max-player)]
    (println "initial score : " initial-score)
    (println "max player symbol " max-player-symbol)

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

    (println "best position is " best-position)
    (println "best score is " best-score)

    best-position))
