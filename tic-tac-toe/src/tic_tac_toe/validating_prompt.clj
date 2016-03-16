(ns tic-tac-toe.validating-prompt
  (:require [tic-tac-toe.writer :as writer]
            [tic-tac-toe.reader :as reader]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.player-options :as player-options]
            ))

(defn- zero-based [index]
  (- index 1))

(defn- show-board-with-invalid-message [error-msg board]
  (writer/display board)
  (error-msg))

(defn- includes? [value valid-range]
  (boolean (some #(= value %) valid-range)))

(defn- validate-input-is-within-range [input valid-range error-msg]
  (if (includes? input (valid-range))
    true
    (do
      (error-msg)
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
      false
      )
    ))

(defn valid-next-move[board]
  (writer/prompt-for-next-move)

  (let [input (reader/read-input)]

    (if (and
          (validate-input-is-numeric input #(show-board-with-invalid-message writer/not-numeric-message board))
          (validate-input-is-within-range
            (zero-based (to-number input))
            #(board/indicies-of-free-spaces board)
            #(show-board-with-invalid-message writer/invalid-space-message board)
            ))
      (zero-based (to-number input))
      (valid-next-move board)
      )
    )
  )

(defn valid-player-option[]
  (writer/prompt-for-player-option)

  (let [input (reader/read-input)]
    (if
      (and
        (validate-input-is-numeric input writer/not-numeric-message)
        (validate-input-is-within-range
          (to-number input)
          player-options/valid-player-options
          writer/invalid-player-option-message
          )
        )
      (to-number input)
      (valid-player-option))))
