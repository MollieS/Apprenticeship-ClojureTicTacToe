(ns tic-tac-toe.validating-prompt
  (:require [tic-tac-toe.writer :as writer]
            [tic-tac-toe.reader :as reader]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.player-options :as player-options]
            ))

(defn- to-number [input]
  (Integer/parseInt input))

(defn- zero-based-number [index]
  (- (to-number index) 1))

(defn- show-board-with-invalid-message [error-msg board]
  (writer/display board)
  (error-msg))

(defn- includes? [value valid-range]
  (boolean (some #(= value %) valid-range)))

(defn- capitalise[input]
 (clojure.string/upper-case input))

(defn- validate-input-is-within-range [input valid-range error-msg]
  (if (includes? input (valid-range))
    true
    (do
      (error-msg)
      false)))

(defn- validate-input-is-numeric [input error-msg]
  (try
    (to-number input)
    true
    (catch Exception e
      (error-msg)
      false)))

(defn- validation-criteria-for-player-option [input]
  (and
    (validate-input-is-numeric input writer/not-numeric-message)
    (validate-input-is-within-range
      (to-number input)
      player-options/valid-player-options
      writer/invalid-player-option-message)))

(defn- validation-criteria-for-next-move [board input]
  (and
    (validate-input-is-numeric input #(show-board-with-invalid-message writer/not-numeric-message board))
    (validate-input-is-within-range
      (zero-based-number input)
      #(board/indicies-of-free-spaces board)
      #(show-board-with-invalid-message writer/invalid-space-message board))))

(defn- validate-input-with-reprompt [prompt-user valid-conditions format-input]
  (prompt-user)

  (let [input (reader/read-input)]
    (if (valid-conditions input)
      (format-input input)
      (validate-input-with-reprompt prompt-user valid-conditions format-input))))

(defn get-valid-player-option[]
  (validate-input-with-reprompt
    writer/prompt-for-player-option
    validation-criteria-for-player-option
    to-number))

(defn get-valid-next-move[board]
  (validate-input-with-reprompt
    writer/prompt-for-next-move
    #(validation-criteria-for-next-move board %)
    zero-based-number))

(defn get-replay-option []
  (writer/prompt-for-replay)
  (let [input (reader/read-input)]
    (capitalise input)))
