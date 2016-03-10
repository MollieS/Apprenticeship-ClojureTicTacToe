(ns tic-tac-toe.board
  (:require [tic-tac-toe.marks :refer :all]))

(def dimension 3)

(defn create [board-config]
  board-config)

(defn place-mark[board mark index]
  (assoc board index mark))

(defn get-mark-at-index [board index]
  (get board index))

(defn get-rows[board]
  [
   (subvec board 0 dimension)
   (subvec board dimension (* 2 dimension))
   (subvec board (* 2 dimension) (* 3 dimension))
   ])

(defn- winning-row? [rows]
  (some true? (map
                (fn[[one two three]] (and (= one two three) (not (= one nil))))
                rows )))

(defn- get-columns[board]
  (get-rows (vec (flatten (conj (map (fn[x] (first x)) (get-rows board))
                                (map (fn[x] (second x) ) (get-rows board))
                                (map (fn[x] (nth x 2)) (get-rows board)))))))

(defn- get-diagonals[board]
  [
   (vector
     (get-mark-at-index board 0)
     (get-mark-at-index board 4)
     (get-mark-at-index board 8))
   (vector
     (get-mark-at-index board 2)
     (get-mark-at-index board 4)
     (get-mark-at-index board 6))
   ]
  )

(defn winning-line? [board]
  (if (= true (or
                (winning-row? (get-rows board))
                (winning-row? (get-columns board))
                (winning-row? (get-diagonals board))
                ))
    true
    false
    )
  )

(defn- find-winning-symbol [rows]
  (first (filter (fn[x] (not (= x nil))) (map (fn[[one two three]] (if (and (= one two three) (not(= one nil))) one nil)) rows))))

(defn winning-symbol [board]
  (cond
    (winning-row? (get-rows board)) (find-winning-symbol (get-rows board))
    (winning-row? (get-columns board)) (find-winning-symbol (get-columns board))
    (winning-row? (get-diagonals board)) (find-winning-symbol (get-diagonals board))
    )
  )

(defn free-spaces? [board]
  (> (count (filter (fn[x] (= nil x)) board)) 0))

(defn indicies-of-free-spaces [board]
  (map (fn[[index value]] (+ index 1)) (filter #(= nil (second %))(map-indexed vector board))))
