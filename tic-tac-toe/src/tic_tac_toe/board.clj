(ns tic-tac-toe.board)

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
  (some true? (map (fn[[one two three]] (and (= one two three) (not (= one nil)))) rows )))

(defn get-columns[board]
  (get-rows (vec (flatten (conj (map (fn[x] (first x)) (get-rows board))
                                (map (fn[x] (second x) ) (get-rows board))
                                (map (fn[x] (nth x 2)) (get-rows board)))))))

(defn winning-line? [board]
  (if (= true (or
                (winning-row? (get-rows board))
                (winning-row? (get-columns board))
                ))
    true
    false
    )
  )

