(ns tic-tac-toe.validating-prompt-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.validating-prompt :refer :all]
            [tic-tac-toe.writer :refer :all]
            [tic-tac-toe.marks :refer :all]))

(defn- empty-board[]
  (vec (repeat 9 nil)))

(defn- zero-based [input]
  (- input 1))

(describe "Prompt"
          (around [it]
                  (with-out-str (it)))

          (it "asks for input and reads it in"
              (should= (zero-based 5)
                       (with-in-str "5\n"
                         (valid-next-move (empty-board)))))

          (it "reprompts until a numeric input is provided"
              (should-invoke non-numeric-input {:times 1}
                             (should-invoke prompt-for-next-move {:times 2}
                                            (should= (zero-based 1)
                                                     (with-in-str "A\n1\n"
                                                       (valid-next-move (empty-board)))))))


          (it "reprompts until a appropriate number is provided"
              (should-invoke not-free-space {:times 2}
                             (should-invoke prompt-for-next-move {:times 3}
                                            (should= (zero-based 5)
                                                     (with-in-str "44\n0\n5"
                                                       (valid-next-move (empty-board)))))))

          (it "gives different error messages depending on the invalid input"
              (should-invoke not-free-space {:times 1}
                             (should-invoke non-numeric-input {:times 1}
                                            (with-in-str "1\nG\n9\n"
                                              (valid-next-move [X nil nil nil nil nil nil nil nil]))))))
