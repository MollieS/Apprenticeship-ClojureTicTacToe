(ns tic-tac-toe.human-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.human-player :refer :all]
            [tic-tac-toe.validating-prompt :as prompt]
            [tic-tac-toe.writer :as writer]))

(defn- empty-board []
  (vec (repeat 9 nil)))

(describe "Player"
          (around [it]
                  (with-out-str (it)))

          (it "provides valid move through commandline prompt"
              (should-invoke prompt/get-valid-next-move {:times 1}
                             (choose-move (empty-board))))

          (it "displays the board when user enters move"
              (should-invoke writer/display {:times 1}
                             (with-in-str "2\n"
                               (choose-move (empty-board))))))
