(ns tic-tac-toe.board)

(defn create [board-config]
  board-config)

(defn place-mark[board mark index]
  (assoc board index mark))

(defn get-mark-at-index [board index]
  (get board index))

(defn get-rows[board]
  [(subvec board 0 3) (subvec board 3 6) (subvec board 6 9)]
  )
