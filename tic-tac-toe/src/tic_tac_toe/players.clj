(ns tic-tac-toe.players
  (:require [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.human-player :as human-player]
            [tic-tac-toe.minimax-player :as minimax-player]
            [tic-tac-toe.random-player :as random-player]
            [tic-tac-toe.player-options :as player-options]))

(defn- setup-players [first-player second-player]
  { X first-player
   O second-player})

(defn configure-players [game-option]
  (cond
    (= game-option player-options/human-human-id) (setup-players human-player/choose-move human-player/choose-move)
    (= game-option player-options/random-human-id) (setup-players random-player/choose-move human-player/choose-move)
    (= game-option player-options/human-random-id) (setup-players human-player/choose-move random-player/choose-move)
    (= game-option player-options/unbeatable-human-id) (setup-players minimax-player/choose-move human-player/choose-move)
    (= game-option player-options/human-unbeatable-id) (setup-players human-player/choose-move minimax-player/choose-move)
    (= game-option player-options/random-unbeatable-id) (setup-players random-player/delayed-random-move minimax-player/delayed-unbeatable-move)
    (= game-option player-options/unbeatable-random-id) (setup-players minimax-player/delayed-unbeatable-move random-player/delayed-random-move)
    (= game-option player-options/random-random-id) (setup-players random-player/delayed-random-move random-player/delayed-random-move )
    (= game-option player-options/unbeatable-unbeatable-id) (setup-players minimax-player/delayed-unbeatable-move minimax-player/delayed-unbeatable-move)))
