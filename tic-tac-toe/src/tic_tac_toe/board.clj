(ns tic-tac-toe.board)

(def dimension 3)

(defn- three-matching-player-symbols? [row]
  (let [[one two three] row]
    (and (= one two three) (not(= one nil)))))

(defn- has-winning-row? [rows]
  (some true? (map three-matching-player-symbols? rows)))

(def winning-row? (memoize has-winning-row?))

(defn- get-winning-symbol [rows]
  (first
    (remove nil?
            (map (fn[row] (if (three-matching-player-symbols? row) (first row) nil)) rows))))

(def find-winning-symbol (memoize get-winning-symbol))

(defn create-empty-board []
  (vec (repeat 9 nil)))

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

(defn get-columns[board]
  (get-rows (vec (flatten (conj (map (fn[row] (first row)) (get-rows board))
                                (map (fn[row] (second row) ) (get-rows board))
                                (map (fn[row] (nth row 2)) (get-rows board)))))))
(defn get-diagonals[board]
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

(defn has-winning-line? [board]
  (boolean (or
             (winning-row? (get-rows board))
             (winning-row? (get-columns board))
             (winning-row? (get-diagonals board)))))

(def winning-line? (memoize has-winning-line?))

(defn get-winning-symbol [board]
  (cond
    (winning-row? (get-rows board)) (find-winning-symbol (get-rows board))
    (winning-row? (get-columns board)) (find-winning-symbol (get-columns board))
    (winning-row? (get-diagonals board)) (find-winning-symbol (get-diagonals board))))

(def winning-symbol (memoize get-winning-symbol))

(defn free-spaces? [board]
  (boolean (some nil? board)))

(defn indicies-of-free-spaces [board]
  (map (fn[[index value]] index) (filter #(= nil (second %))(map-indexed vector board))))
