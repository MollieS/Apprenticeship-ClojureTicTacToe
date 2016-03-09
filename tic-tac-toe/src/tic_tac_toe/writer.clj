(ns tic-tac-toe.writer)

(defn prompt-for-next-move []
  (println "Please enter your next move")

  (defn invalid-input[]
    (println "Invalid input!"))

  (defn draw[]
    (println "The game was a draw"))

  (defn win[player-symbol]
    (println "The game was won by" player-symbol)))


