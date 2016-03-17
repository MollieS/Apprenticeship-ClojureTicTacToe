(ns tic-tac-toe.human-player
  (:require [tic-tac-toe.validating-prompt :as prompt]))

  (defn choose-move [board]
    (prompt/get-valid-next-move board))
