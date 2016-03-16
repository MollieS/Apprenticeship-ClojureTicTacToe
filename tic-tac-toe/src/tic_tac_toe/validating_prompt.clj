(ns tic-tac-toe.validating-prompt
  (:require [tic-tac-toe.writer :as writer]
            [tic-tac-toe.reader :as reader]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.player-options :as player-options]
            ))

(defn- zero-based [index]
  (- index 1))

(defn- within-range[value valid-range]
   (boolean (some #(= value %) valid-range)))

(defn- show-board-with-invalid-message [error-msg board]
  (writer/display board)
  (error-msg))

(defn- validate-input-is-within-range [provided-index board]
  (if (within-range provided-index (board/indicies-of-free-spaces board))
    true
    (do
      (show-board-with-invalid-message writer/invalid-space-message board)
      false
      ))
  )

(defn- to-number [input]
  (Integer/parseInt input))

(defn- validate-player-option-is-within-range [input]
     (if (within-range (to-number input) (player-options/valid-player-options)) ; rename!
    true
    (do
      (writer/invalid-player-option-message)
      false
      ))
  )

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
          (validate-input-is-within-range (zero-based (to-number input)) board))
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
        (validate-player-option-is-within-range input ))
      (to-number input)
      (valid-player-option))))
