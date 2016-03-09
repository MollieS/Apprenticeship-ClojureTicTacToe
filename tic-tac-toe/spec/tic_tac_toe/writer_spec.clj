(ns tic-tac-toe.writer-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.writer :refer :all]))

(describe "Command Line Writer"
          (around [it]
                  (with-out-str (it)))


          (it "asks for a move"
              (should= "Please enter your next move\n"
                       (with-out-str (prompt-for-next-move))))


          (it "displays invalid input message"
              (should= "Invalid input!\n"
                       (with-out-str (invalid-input))))

          (it "displays draw message"
              (should= "The game was a draw\n"
                       (with-out-str (draw))))

         (it "displays winning message"
            (should= "The game was won by X\n"
                    (with-out-str(win "X")))))

