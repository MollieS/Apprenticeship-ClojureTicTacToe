(ns tic-tac-toe.player-options)

(def human-human-id 1)
(def random-human-id 2)
(def human-random-id 3)

(def player-options
  { human-human-id "Human vs Human"
    random-human-id "Random vs Human"
    human-random-id "Human vs Random"})

(defn display[]
  (apply str (map (fn[[k v]] (str "(" k ") " v "\n ")) player-options)))

(defn valid-player-options[]
  (keys player-options))