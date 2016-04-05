(ns tic-tac-toe.board)

(def dimension 3)

(defn- three-matching-player-symbols? [row]
  (let [[one two three] row]
    (and (= one two three) (not(= one nil)))))

(defn- winning-row? [rows]
  (some true? (map three-matching-player-symbols? rows)))

(def memoized-winning-row? (memoize winning-row?))

(defn- find-winning-symbol [rows]
  (first
    (remove nil?
            (map (fn[row] (if (three-matching-player-symbols? row) (first row) nil)) rows))))

(def memoized-find-winning-symbol (memoize find-winning-symbol))

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

(defn cached-winning-line? [board]
  (boolean (or
             (memoized-winning-row? (get-rows board))
             (memoized-winning-row? (get-columns board))
             (memoized-winning-row? (get-diagonals board)))))

(def winning-line? (memoize cached-winning-line?))

(defn cached-winning-symbol [board]
  (cond
    (memoized-winning-row? (get-rows board)) (memoized-find-winning-symbol (get-rows board))
    (memoized-winning-row? (get-columns board)) (memoized-find-winning-symbol (get-columns board))
    (memoized-winning-row? (get-diagonals board)) (memoized-find-winning-symbol (get-diagonals board))))

(def winning-symbol (memoize cached-winning-symbol))

(defn free-spaces? [board]
  (boolean (some nil? board)))

(defn indicies-of-free-spaces [board]
  (map (fn[[index value]] index) (filter #(= nil (second %))(map-indexed vector board))))
