(ns tic-tac-toe.players-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.players :refer :all]
            [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.human-player :as human-player]
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
                         (get human-random O)))))
