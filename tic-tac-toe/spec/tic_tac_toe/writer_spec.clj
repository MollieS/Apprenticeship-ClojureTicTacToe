(ns tic-tac-toe.writer-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.marks :refer :all]
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
                       (with-out-str(win "X"))))

          (it "displays empty board"
              (should= "\n [ 1 ] [ 2 ] [ 3 ] \n [ 4 ] [ 5 ] [ 6 ] \n [ 7 ] [ 8 ] [ 9 ]\n"
                       (with-out-str (display (vec (repeat 9 nil))))))

          (it "displays board with occupied cells"
              (should= "\n [ X ] [ 2 ] [ O ] \n [ 4 ] [ 5 ] [ X ] \n [ 7 ] [ 8 ] [ 9 ]\n"
                       (with-out-str (display [X nil O nil nil X nil nil nil])))))
