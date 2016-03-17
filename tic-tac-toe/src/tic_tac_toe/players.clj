(ns tic-tac-toe.players
  (:require [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.player :as player]))


(def human-human
  { X #(player/choose-move %)
    O #(player/choose-move %) }
  )
