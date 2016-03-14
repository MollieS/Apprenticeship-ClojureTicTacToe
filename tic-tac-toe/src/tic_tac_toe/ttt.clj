(ns tic-tac-toe.ttt
  (:require [tic-tac-toe.ttt :refer :all]
            [tic-tac-toe.prompt :refer :all]
            [tic-tac-toe.writer :refer :all]
            [tic-tac-toe.board :refer :all]
            [tic-tac-toe.marks :refer :all]))

(defn play [board]
    (display board)
    (display (place-mark board (next-mark board) (valid-next-move board) ))
  ;;check for win
  )


(defn start[]
  (let [board (create (vec (repeat 9 nil)))]
    (play board)))

(defn -main[]
  (start))
