(ns tic-tac-toe.player-options)

(defn player-options[]
  ["Human vs Human"])

(defn display[]
  (apply str (map (fn[[k v]] (str "(" (+ 1 k) ") " v "\n")) (map-indexed vector (player-options)))))
