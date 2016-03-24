(ns tic-tac-toe.validating-prompt-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.validating-prompt :refer :all]
            [tic-tac-toe.marks :refer :all]
            [tic-tac-toe.writer :as writer]))

(defn- empty-board[]
  (vec (repeat 9 nil)))

(defn- zero-based [input]
  (- input 1))

(describe "Prompt"
          (around [it]
                  (with-out-str (it)))

          (it "asks for next move and reads it in"
              (should= (zero-based 5)
                       (with-in-str "5\n"
                         (get-valid-next-move (empty-board)))))

          (it "reprompts until a numeric input is provided"
              (should-invoke writer/not-numeric-message {:times 1}
                             (should-invoke writer/prompt-for-next-move {:times 2}
                                            (should= (zero-based 1)
                                                     (with-in-str "A\n1\n"
                                                       (get-valid-next-move (empty-board)))))))

          (it "reprompts for next move until a appropriate value is provided"
              (should-invoke writer/invalid-space-message {:times 2}
                             (should-invoke writer/prompt-for-next-move {:times 3}
                                            (should= (zero-based 5)
                                                     (with-in-str "44\n0\n5"
                                                       (get-valid-next-move (empty-board)))))))

          (it "displays board when an invalid input is provided for next move"
              (should-invoke writer/invalid-space-message {:times 2}
                             (should-invoke writer/display {:times 2}
                                            (should= (zero-based 5)
                                                     (with-in-str "44\n0\n5"
                                                       (get-valid-next-move (empty-board)))))))

          (it "gives different error messages depending on the invalid input for next move"
              (should-invoke writer/invalid-space-message {:times 1}
                             (should-invoke writer/not-numeric-message {:times 1}
                                            (with-in-str "1\nG\n9\n"
                                              (get-valid-next-move [X nil nil nil nil nil nil nil nil])))))

          (it "prompts and reads in player option"
              (should-invoke writer/prompt-for-player-option {:times 1}
                             (should= 1
                                      (with-in-str "1\n"
                                          (get-valid-player-option)))))

          (it "reprompts until a numeric player option is provided"
              (should-invoke writer/not-numeric-message {:times 1}
                             (should-invoke writer/prompt-for-player-option {:times 2}
                                            (should= 1
                                                     (with-in-str "A\n1\n"
                                                       (get-valid-player-option))))))

          (it "reprompts until a number within the correct range is provided for player option"
              (should-invoke writer/invalid-player-option-message {:times 1}
                             (should-invoke writer/prompt-for-player-option {:times 2}
                                            (should= 1
                                                     (with-in-str "6\n1\n"
                                                       (get-valid-player-option))))))

         (it "prompts and reads in replay option"
              (should-invoke writer/prompt-for-replay {:times 1}
                             (should= "Y"
                                      (with-in-str "Y\n"
                                          (get-valid-replay-option)))))

         (it "translates replay option to capital"
              (should-invoke writer/prompt-for-replay {:times 1}
                             (should= "Y"
                                      (with-in-str "y\n"
                                          (get-valid-replay-option)))))
          )
