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

(defn- show-invalid-message [error-msg board]
  (writer/display board)
  (error-msg))

(defn- validate-input-is-within-range [provided-index board]
  (if (vacant-space provided-index (board/indicies-of-free-spaces board))
    true
    (do
      (show-invalid-message writer/invalid-space-message board)
      false
      ))
  )

(defn- to-number [input]
  (Integer/parseInt input))

(defn- validate-input-is-numeric [input board]
  (try
    (to-number input)
    true
    (catch Exception e
      (show-invalid-message writer/not-numeric-message board)
      false
      )
    ))

(defn valid-next-move[board]
  (writer/prompt-for-next-move)

  (let [input (reader/read-input)]

    (if (and (validate-input-is-numeric input board)
             (validate-input-is-within-range (zero-based (to-number input)) board))
      (zero-based (to-number input))
      (valid-next-move board)
      )
    )
  )

(defn valid-player-option[]
  (writer/prompt-for-player-option)
  (to-number (reader/read-input))
  )
