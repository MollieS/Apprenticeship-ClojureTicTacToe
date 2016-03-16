(ns tic-tac-toe.player-options)

(defn player-options[]
  { 1 "Human vs Human"})

(defn display[]
  (apply str (map (fn[[k v]] (str "(" k ") " v "\n")) (player-options))))
