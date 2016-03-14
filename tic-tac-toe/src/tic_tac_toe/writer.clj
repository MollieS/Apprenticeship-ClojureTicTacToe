(ns tic-tac-toe.writer
  (:require [tic-tac-toe.board :refer :all]))

(defn- one-based[index]
  (+ 1 index))

(defn- replace-empty-cells-with-index [board]
  (map-indexed vector board))

(defn- unoccupied [cell]
  (= cell nil))

(defn- board-with-one-based-indicies [board]
  (map (fn[[cell-index cell-value]] (if (unoccupied cell-value) (one-based cell-index) cell-value)) (replace-empty-cells-with-index board)))

(defn- border [board]
  (let [[cell-one cell-two cell-three] board]
    (map (fn[[cell-one cell-two cell-three]] (str "\n [ " cell-one " ] [ " cell-two " ] [ " cell-three " ]")) board)))

(defn prompt-for-next-move []
  (println "Please enter your next move"))

(defn non-numeric-input[]
  (println "Not a numeric input!"))

(defn not-free-space[]
  (println "Not a valid position!"))

(defn draw[]
  (println "The game was a draw"))

(defn win[player-symbol]
  (println "The game was won by" player-symbol))

(defn display [board]
  (apply println (border (get-rows (vec (board-with-one-based-indicies board))))))

