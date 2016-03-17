(ns tic-tac-toe.players
  (:require [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.human-player :as human-player]
            [tic-tac-toe.random-player :as random-player]))


(def human-human
  { X #(human-player/choose-move %)
   O #(human-player/choose-move %) })

(def random-human
  { X #(random-player/choose-move %)
   O #(human-player/choose-move %) })

(def human-random
  { X #(human-player/choose-move %)
   O #(random-player/choose-move %) })


(defn configure-players [game-option]
  (cons
    (= game-option 1) human-human
    (= game-option 2) random-human
    (= game-option 3) human-random
    )
  )
