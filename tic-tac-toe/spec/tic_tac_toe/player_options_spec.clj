(ns tic-tac-toe.player-options-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player-options :refer :all]))

(describe "Player Options"

          (it "has 3 categories"
              (should= 3
                       (count player-options)))

          (it "has display format"
              (should= "(1) Human vs Human\n(2) Random vs Human\n(3) Human vs Random\n"
                       (display)))

         (it "valid choices"
          (should= '(1 2 3)
                  (valid-player-options))))
