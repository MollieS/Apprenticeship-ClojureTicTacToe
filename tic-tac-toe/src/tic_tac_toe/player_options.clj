(ns tic-tac-toe.player-options)

(def human-human-id 1)
(def random-human-id 2)
(def human-random-id 3)
(def human-unbeatable-id 4)
(def unbeatable-human-id 5)

(def player-options
  { human-human-id "Human vs Human"
    random-human-id "Random vs Human"
    human-random-id "Human vs Random"
    human-unbeatable-id "Human vs Unbeatable"
    unbeatable-human-id "Unbeatable vs Human"})

(defn display[]
  (apply str (map (fn[[k v]] (str "(" k ") " v "\n ")) player-options)))

(defn valid-player-options[]
  (keys player-options))
