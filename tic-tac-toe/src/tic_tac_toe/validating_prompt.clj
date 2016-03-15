(ns tic-tac-toe.validating-prompt
  (:require [tic-tac-toe.writer :as writer]
            [tic-tac-toe.reader :as reader]
            [tic-tac-toe.board :as board]
            ))

(defn- zero-based [index]
  (- index 1))

(defn- invalid-space[]
  (writer/invalid-space-message)
  false)

(defn- vacant-space[index spaces-on-board]
  (boolean (some #(= index %) spaces-on-board))
  )

(defn- within-range? [provided-index spaces-on-board]
  (if (vacant-space provided-index spaces-on-board)
    true
    (invalid-space)
    )
  )

(defn- to-number [input]
  (Integer/parseInt input))

(defn- numeric? [input]
  (try
    (to-number input)
      true
    (catch Exception e
      (writer/not-numeric-message)
      false
      )
    ))

(defn valid-next-move[board]
  (writer/prompt-for-next-move)

  (let [input (reader/read-input)]

    (if (and (numeric? input)
             (within-range? (zero-based (to-number input)) (board/indicies-of-free-spaces board)))
      (zero-based (to-number input))
      (valid-next-move board)
      )
    )
  )
