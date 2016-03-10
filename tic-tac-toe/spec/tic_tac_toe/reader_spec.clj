(ns tic-tac-toe.reader-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.reader :refer :all]))

(describe "Command Line Reader"
          (it "reads input"
              (should= "1"
                       (with-in-str "1"
                         (read-input)))))
