(ns tic-tac-toe.minimax-player
  (:require [tic-tac-toe.minimax-player :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.marks :as marks]))

(def DRAW-SCORE 0)
(def MAX-SCORE 10)
(def MIN-SCORE -10)
(def DUMMY-POSITION -1)

(defn- max-player-winning-score [depth]
  (println "max player score is" (+ MAX-SCORE depth))
  (+ MAX-SCORE depth))

(defn- min-player-winning-score [depth]
  (- MIN-SCORE depth))

(defn- get-winning-score [max-player-symbol board depth]
  (if (= max-player-symbol (board/winning-symbol board))
    (max-player-winning-score depth)
    (min-player-winning-score depth)))

(defn calculate-score [board max-player-symbol depth]
  (if (board/winning-line? board)
    (get-winning-score max-player-symbol board depth)
    DRAW-SCORE
    )
  )

(defn- find-position [best-score [head & tail]]
  (let [[position score] head]
    (if (= score best-score)
      position
      (find-position best-score tail))))

(defn- find-max [scores]
  (let [max-score (apply max (map (fn [[position score]] score) scores))
        max-position (find-position max-score scores)]
    [max-position max-score]))

(defn- find-min [scores]
  (let [min-score (apply min (map (fn [[position score]] score) scores))
        min-position (find-position min-score scores)]
    [min-position min-score]))

(defn minimax [board depth is-max-player max-player-symbol]
  (let [scores [[]]]

    (if (or (= depth 0)
            (board/winning-line? board))
      [DUMMY-POSITION (calculate-score board max-player-symbol depth)])

    (let [[first-free-spot & tail] (board/indicies-of-free-spaces board)]
      ; not sure about this if statement but can't force a return from base condition
      (if (not (= nil first-free-spot))
        (do
          (let [ updated-board (board/place-mark board (marks/next-mark board) first-free-spot)
                [DUMMY-POSITION value]  (minimax updated-board (dec depth) (not is-max-player) max-player-symbol)
                scores (conj [] [first-free-spot (second value)])])))
      ; because everything is immutable, how do I add to the scores eacch time
      ; cant pass it in the recursion as i need the return value fo the recursive loop
      (println "scores are " scores)
      (if is-max-player
        (find-max scores)
        (find-min scores)
        )
      )
    )
  )

(defn choose-move [board]
  (let [is-max-player true
        max-player-symbol (marks/next-mark board)
        [best-position best-score]
        (minimax
          board
          (count (board/indicies-of-free-spaces board))
          is-max-player
          max-player-symbol)]
    best-position
    )
  )
