(ns tic-tac-toe.player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player :refer :all]
            [tic-tac-toe.validating-prompt :as prompt]))

(defn- empty-board []
  (vec (repeat 9 nil)))

(describe "Player"
          (it "provides valid move through commandline prompt"
              (should-invoke prompt/get-valid-next-move {:times 1}
                             (choose-move (empty-board)))
              ))
