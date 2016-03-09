(ns tic-tac-toe.prompt-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.prompt :refer :all]
            [tic-tac-toe.writer :refer :all]))

(defn make-input [coll]
  (apply str (interleave coll)))

(describe "Prompt"
          (it "asks for input and reads it in"
              (should= 5
                       (with-in-str "5\n"
                         (valid-next-move)))
              )

          (it "reprompts when a valid numeric value input"
              (should-invoke invalid-input {:times 1}
                             (should-invoke prompt-for-next-move {:times 2}
                                            (should= 1
                                                     (with-in-str "A\n1\n"
                                                       (valid-next-move)))))))


