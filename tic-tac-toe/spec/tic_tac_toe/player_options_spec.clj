(ns tic-tac-toe.player-options-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player-options :refer :all]))

(describe "Player Options"

          (it "has 9 categories"
              (should= 9
                       (count player-options)))

          (it "has display format"
              (should= "(1) Human vs Human\n (2) Random vs Human\n (3) Human vs Random\n (4) Unbeatable vs Human\n (5) Human vs Unbeatable\n (6) Random vs Unbeatable\n (7) Unbeatable vs Random\n (8) Random vs Random\n (9) Unbeatable vs Unbeatable\n "
                       (display)))

          (it "valid choices"
              (should= '(1 2 3 4 5 6 7 8 9)
                       (valid-player-options)))

          (it "has an id for human vs human"
              (should= 1
                       human-human-id))

          (it "has an id for random vs human"
              (should= 2 random-human-id))

          (it "has an id for human vs random"
              (should= 3 human-random-id))

          (it "has an id for unbeatable vs human"
              (should= 4 unbeatable-human-id))

          (it "has an id for human vs unbeatable"
              (should= 5 human-unbeatable-id))

          (it "has an id for random vs unbeatable"
              (should= 6 random-unbeatable-id))

          (it "has an id for unbeatable vs random"
              (should= 7 unbeatable-random-id))

          (it "has an id for random vs random"
              (should= 8 random-random-id))

          (it "has an id for unbeatable vs unbeatable"
              (should= 9 unbeatable-unbeatable-id)))
