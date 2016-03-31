(ns tic-tac-toe.players
  (:require [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.human-player :as human-player]
            [tic-tac-toe.minimax-player :as minimax-player]
            [tic-tac-toe.random-player :as random-player]
            [tic-tac-toe.player-options :as player-options]
            ))


(def human-human
  { X human-player/choose-move
    O human-player/choose-move })

(def random-human
  { X random-player/choose-move
    O human-player/choose-move })

(def human-random
  { X human-player/choose-move
    O random-player/choose-move })

(def human-unbeatable
  { X human-player/choose-move
    O minimax-player/choose-move })

(def unbeatable-human
  { X minimax-player/choose-move
    O human-player/choose-move })

(defn configure-players [game-option]
  (cond
    (= game-option player-options/human-human-id) human-human
    (= game-option player-options/random-human-id) random-human
    (= game-option player-options/human-random-id) human-random
    (= game-option player-options/human-unbeatable-id) human-unbeatable
    (= game-option player-options/unbeatable-human-id) unbeatable-human
    )
  )
