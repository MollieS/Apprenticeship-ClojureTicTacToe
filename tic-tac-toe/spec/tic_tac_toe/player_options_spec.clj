(ns tic-tac-toe.player-options-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player-options :refer :all]))

(describe "Player Options"

          (it "has 5 categories"
              (should= 5
                       (count player-options)))

          (it "has display format"
              (should= "(1) Human vs Human\n (2) Random vs Human\n (3) Human vs Random\n (4) Human vs Unbeatable\n (5) Unbeatable vs Human\n "
                       (display)))

          (it "valid choices"
              (should= '(1 2 3 4 5)
                       (valid-player-options)))

          (it "has an id for human vs human"
              (should= 1
                       human-human-id))

          (it "has an id for random vs human"
              (should= 2 random-human-id))

          (it "has an id for human vs random"
              (should= 3 human-random-id))

          (it "has an id for human vs unbeatable"
              (should= 4 human-unbeatable-id))

         (it "has an id for unbeatable vs human"
             (should= 5 unbeatable-human-id)))
