(ns tic-tac-toe.board)

(defn create [board-config]
  board-config
  )

(defn place-mark[board mark index]
  (assoc board index mark)
  )
