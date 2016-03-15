(ns tic-tac-toe.validating-prompt
  (:require [tic-tac-toe.writer :as writer]
            [tic-tac-toe.reader :as reader]
            [tic-tac-toe.board :as board]
            ))

(defn- zero-based [index]
  (- index 1))

(defn- vacant-space[index spaces-on-board]
  (boolean (some #(= index %) spaces-on-board))
  )

(defn- within-range? [provided-index board]
  (if (vacant-space provided-index (board/indicies-of-free-spaces board))
    true
    false
    )
  )

(defn- to-number [input]
  (Integer/parseInt input))

(defn- numeric? [input]
  (try
    (to-number input)
    true
    (catch Exception e
      false
      )
    ))

(defn- show-invalid-message [error-msg board]
  (writer/display board)
  (error-msg)
  )

(defn- not-numeric [input]
  (not (numeric? input)))

(defn- not-valid-space [input board]
  (not (within-range? (zero-based (to-number input)) board)))

  (defn valid-next-move[board]
    (writer/prompt-for-next-move)

    (let [input (reader/read-input)]

      (if (not-numeric input)
        (do
          (show-invalid-message writer/not-numeric-message board)
          (valid-next-move board)
          )

        (if (not-valid-space input board)
          (do
            (show-invalid-message writer/invalid-space-message board)
            (valid-next-move board)
            )
          (zero-based (to-number input))
          )
        )
      )
    )
