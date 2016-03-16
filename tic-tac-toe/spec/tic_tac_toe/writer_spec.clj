(ns tic-tac-toe.writer-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.writer :refer :all]
            [tic-tac-toe.marks :refer :all]))

(describe "Command Line Writer"
          (around [it]
                  (with-out-str (it)))

          (it "asks for a move"
              (should= "Please enter your next move\n"
                       (with-out-str (prompt-for-next-move))))

          (it "displays invalid message when input is not numeric"
              (should= "Not a numeric input!\n"
                       (with-out-str (not-numeric-message))))

          (it "displays invalid message when input is not a valid position on the board"
              (should= "Not a valid position!\n"
                       (with-out-str (invalid-space-message))))

          (it "displays draw message"
              (should= "The game was a draw\n"
                       (with-out-str (draw-message))))

          (it "displays winning message"
              (should= "The game was won by X\n"
                       (with-out-str(win-message "X"))))

          (it "displays empty board"
              (should= "\n [ 1 ] [ 2 ] [ 3 ] \n [ 4 ] [ 5 ] [ 6 ] \n [ 7 ] [ 8 ] [ 9 ]\n"
                       (with-out-str (display (vec (repeat 9 nil))))))

          (it "displays board with occupied cells"
              (should= "\n [ X ] [ 2 ] [ O ] \n [ 4 ] [ 5 ] [ X ] \n [ 7 ] [ 8 ] [ 9 ]\n"
                       (with-out-str (display [X nil O nil nil X nil nil nil]))))

         (it "displays player options"
            (should= "Choose player option:\n(1) Human vs Human\n"
                    (with-out-str ( prompt-for-player-option))))

         (it "displays invalid message when input is not a valid player option"
            (should= "Not a valid player option!\n"
                    (with-out-str (invalid-player-option-message)))))
