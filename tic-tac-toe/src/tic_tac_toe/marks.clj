(ns tic-tac-toe.marks)

(def X 'X)
(def O 'O)

(defn- count-symbol [mark board]
  (count (filter #{mark} board)))

(defn next-mark [board]
  (if (= (count-symbol X board)
         (count-symbol O board))
    X
    O)
  )
