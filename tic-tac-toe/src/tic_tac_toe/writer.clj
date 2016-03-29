(ns tic-tac-toe.writer
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.player-options :as player-options]
            [tic-tac-toe.replay-options :as replay-option]))

(defn- one-based[index]
  (+ 1 index))

(defn- replace-empty-cells-with-index [board]
  (map-indexed vector board))

(defn- unoccupied [cell]
  (= cell nil))

(defn- board-with-one-based-indicies [board]
  (map (fn[[cell-index cell-value]]
         (if (unoccupied cell-value)
           (one-based cell-index)
           cell-value))
       (replace-empty-cells-with-index board)))

(defn- border [board]
  (let [[cell-one cell-two cell-three] board]
    (map (fn[[cell-one cell-two cell-three]]
           (str "\n [ " cell-one " ] [ " cell-two " ] [ " cell-three " ]")) board)))

(defn prompt-for-next-move []
  (println "Please enter your next move"))

(defn not-numeric-message[]
  (println "Not a numeric input!"))

(defn invalid-space-message[]
  (println "Not a valid position!"))

(defn draw-message[]
  (println "The game was a draw"))

(defn win-message[player-symbol]
  (println "The game was won by" player-symbol))

(defn display [board]
  (apply println (border (board/get-rows (vec (board-with-one-based-indicies board))))))

 (defn prompt-for-player-option []
   (println "Choose player option:\n" (player-options/display)))

(defn invalid-player-option-message[]
  (println "Not a valid player option!"))

(defn prompt-for-replay []
     (println (str "Play again? (" replay-option/replay-option " to replay)")))

(defn goodbye-msg []
  (println "Thanks for playing. Goodbye!"))
