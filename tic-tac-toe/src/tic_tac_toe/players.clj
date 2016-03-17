(ns tic-tac-toe.players
  (:require [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.human-player :as human-player]))


(def human-human
  { X #(human-player/choose-move %)
    O #(human-player/choose-move %) }
  )
