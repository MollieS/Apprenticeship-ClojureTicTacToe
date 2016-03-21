(ns tic-tac-toe.human-player
  (:require [tic-tac-toe.validating-prompt :as prompt]
            [tic-tac-toe.writer :as writer]))

  (defn choose-move [board]
    (writer/display board)
    (prompt/get-valid-next-move board))
