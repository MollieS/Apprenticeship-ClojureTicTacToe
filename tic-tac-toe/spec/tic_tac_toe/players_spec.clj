(ns tic-tac-toe.players-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.players :refer :all]
            [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.player :as player]))


(describe "Players"
          (around [it]
                  (with-out-str (it)))

          (xit "creates human players for X and O"
              ))
