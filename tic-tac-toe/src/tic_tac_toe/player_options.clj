(ns tic-tac-toe.player-options)

(def player-options
  { 1 "Human vs Human"
    2 "Random vs Human"
    3 "Human vs Random"})

(defn display[]
  (apply str (map (fn[[k v]] (str "(" k ") " v "\n ")) player-options)))

(defn valid-player-options[]
 (keys player-options))
