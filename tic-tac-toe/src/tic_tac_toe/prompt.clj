(ns tic-tac-toe.prompt
  (:require [tic-tac-toe.writer :refer :all]
            [tic-tac-toe.reader :refer :all]))

(defn valid-next-move[]
  (prompt-for-next-move)
  (try
    (Integer/parseInt (read-input))
    (catch Exception e
      (invalid-input)
      (valid-next-move))))
