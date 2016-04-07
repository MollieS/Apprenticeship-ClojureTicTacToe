(ns tic-tac-toe.players-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.players :refer :all]
            [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.human-player :as human-player]
            [tic-tac-toe.minimax-player :as minimax-player]
            [tic-tac-toe.random-player :as random-player]))

(describe "Players"
          (around [it]
                  (with-out-str (it)))

          (it "finds player configuration for human vs human"
              (let [human-human (configure-players 1)]
                (should= human-player/choose-move
                         (get human-human X))
                (should= human-player/choose-move
                         (get human-human O))))

          (it "finds player configuration for random vs human"
              (let [random-human (configure-players 2)]
                (should= random-player/choose-move
                         (get random-human X))
                (should= human-player/choose-move
                         (get random-human O))))

          (it "finds player configuration for human vs random"
              (let [human-random (configure-players 3)]
                (should= human-player/choose-move
                         (get human-random X))
                (should= random-player/choose-move
                         (get human-random O))))

          (it "finds player configuration for unbeatable vs human"
              (let [unbeatable-human (configure-players 4)]
                (should= minimax-player/choose-move
                         (get unbeatable-human X))
                (should= human-player/choose-move
                         (get unbeatable-human O))))

          (it "finds player configuration for human vs unbeatable"
              (let [human-unbeatable (configure-players 5)]
                (should= human-player/choose-move
                         (get human-unbeatable X))
                (should= minimax-player/choose-move
                         (get human-unbeatable O))))

          (it "finds player configuration for random vs random"
              (let [random-random (configure-players 6)]
                (should= random-player/delayed-random-move
                         (get random-random X))
                (should= random-player/delayed-random-move
                         (get random-random O))))

         (it "finds player configuration for unbeatable vs unbeatable"
              (let [unbeatable-unbeatable (configure-players 7)]
                (should= minimax-player/delayed-unbeatable-move
                         (get unbeatable-unbeatable X))
                (should= minimax-player/delayed-unbeatable-move
                         (get unbeatable-unbeatable O)))))
