(ns tic-tac-toe.ttt
  (:require [tic-tac-toe.ttt :refer :all]
            [tic-tac-toe.validating-prompt :refer :all]
            [tic-tac-toe.writer :refer :all]
            [tic-tac-toe.board :refer :all]
            [tic-tac-toe.marks :refer :all]))

(defn- empty-board []
(create (vec (repeat 9 nil))))

(defn- has-win? [board]
  (winning-line? board))

(defn- announce-win [board]
  (win (winning-symbol board)))

(defn- no-free-spaces? [board]
(not (free-spaces? board)))

(defn play-move [board]
  (let [updated-board (place-mark board (next-mark board) (valid-next-move board) )]
    (display updated-board)
    (cond
      (has-win? updated-board) (announce-win updated-board)
      (no-free-spaces? updated-board) (draw)
      :else
       (play-move updated-board)
      )
    )
  )

(defn start[]
  (let [board (empty-board)]
    (display board)
    (play-move board)))

(defn -main[]
  (start))
