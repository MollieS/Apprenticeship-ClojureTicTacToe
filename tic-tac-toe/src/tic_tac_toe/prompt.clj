(ns tic-tac-toe.prompt
  (:require [tic-tac-toe.writer :refer :all]
            [tic-tac-toe.reader :refer :all]
            [tic-tac-toe.board :refer :all]
            ))

(defn- zero-based [index]
  (- index 1))

(defn- false-and-publish-error-message[]
  (not-free-space)
  false)

(defn- within-range? [provided-index spaces-on-board]
  (if (boolean (some #(= provided-index %) spaces-on-board))
    true
    (false-and-publish-error-message)
    )
  )

(defn- to-number [input]
  (Integer/parseInt input))

(defn- numeric? [input]
  (try
    (to-number input)
      true
    (catch Exception e
      (non-numeric-input)
      false
      )
    ))

(defn valid-next-move[board]
  (prompt-for-next-move)

  (let [input (read-input)]

    (if (and (numeric? input)
             (within-range? (zero-based (to-number input)) (indicies-of-free-spaces board)))
      (zero-based (to-number input))
      (valid-next-move board)
      )
    )
  )

(defn -main[]
  (valid-next-move [nil nil nil nil nil 'X nil 'O nil]))
