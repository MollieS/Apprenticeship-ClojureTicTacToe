(ns tic-tac-toe.ttt
  (:require [tic-tac-toe.ttt :refer :all]
            [tic-tac-toe.validating-prompt :refer :all]
            [tic-tac-toe.writer :refer :all]
            [tic-tac-toe.board :refer :all]
            [tic-tac-toe.marks :refer :all]))

(defn play [board]
  (let [updated-board (place-mark board (next-mark board) (valid-next-move board) )]
    (display updated-board)
    (cond
      (winning-line? updated-board) (win (winning-symbol updated-board))
      (not (free-spaces? updated-board)) (draw)
      :else
       (play updated-board)
      )
    )
  )

(defn start[]
  (let [board (create (vec (repeat 9 nil)))]
    (display board)
    (play board)))

(defn -main[]
  (start))
