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

(defn- show-board-with-invalid-message [error-msg board]
  (writer/display board)
  (error-msg))

(defn- validate-input-is-within-range [provided-index board]
  (if (vacant-space provided-index (board/indicies-of-free-spaces board))
    true
    (do
      (show-board-with-invalid-message writer/invalid-space-message board)
      false
      ))
  )

(defn- to-number [input]
  (Integer/parseInt input))

(defn- validate-input-is-numeric [input error-msg]
  (try
    (to-number input)
    true
    (catch Exception e
      (error-msg)
      ;(show-board-with-invalid-message writer/not-numeric-message board)
      false
      )
    ))

(defn valid-next-move[board]
  (writer/prompt-for-next-move)

  (let [input (reader/read-input)]

    (if (and (validate-input-is-numeric input #(show-board-with-invalid-message writer/not-numeric-message board))
             (validate-input-is-within-range (zero-based (to-number input)) board))
      (zero-based (to-number input))
      (valid-next-move board)
      )
    )
  )

(defn valid-player-option[]
  (writer/prompt-for-player-option)

  (let [input (reader/read-input)]

    (if (validate-input-is-numeric input writer/not-numeric-message)
  ; is it a key in the player options map
    (to-number input)
    (valid-player-option))))
