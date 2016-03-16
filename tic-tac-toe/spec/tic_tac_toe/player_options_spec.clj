(ns tic-tac-toe.player-options-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player-options :refer :all]))

(describe "Player Options"

          (it "has one category"
              (should= 1
                       (count(player-options))))

          (it "has display format"
              (should= "(1) Human vs Human\n"
                       (display))))
