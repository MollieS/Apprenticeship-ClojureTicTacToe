(ns tic-tac-toe.game

  (:require [tic-tac-toe.validating-prompt :as prompt]
            [tic-tac-toe.writer :as writer]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.marks :as marks]
            [tic-tac-toe.players :as players]
            [tic-tac-toe.replay-options :as replay]))

(defn- empty-board []
  (board/create-empty-board))

(defn- has-win? [board]
  (board/winning-line? board))

(defn- player-choice []
  (prompt/get-valid-player-option))

(defn no-free-spaces? [board]
  (not (board/free-spaces? board)))

(defn announce-win [board]
  (writer/display board)
  (writer/win-message (board/winning-symbol board)))

(defn announce-draw [board]
  (writer/display board)
  (writer/draw-message))

(defn- game-over? [updated-board]
  (or (has-win? updated-board)
      (no-free-spaces? updated-board)))

(defn- announce-result [board]
  (if (has-win? board)
    (announce-win board)
    (announce-draw board)))

(defn- mark-of-next-player [board]
  (marks/next-mark board))

(defn update-board-with-move [players board mark]
  (board/place-mark board mark
                    ((get players mark) board)))

(defn play-move [board players]
  (loop [board board]
    (let [next-mark (mark-of-next-player board)
          updated-board (update-board-with-move players board next-mark)]

      (if (game-over? updated-board)
        (announce-result updated-board)
        (recur updated-board)))))

(defn replay? []
  (= (prompt/get-replay-option) replay/replay-option))

(defn get-players []
  (players/configure-players (player-choice)))

(defn play-game []
  (loop [ ]
    (let [board (empty-board)
          player-choice (get-players)]
      (play-move board player-choice))
    (if (replay?)
      (recur))))

(defn start[]
  (play-game)
  (writer/goodbye-msg))

(defn -main[]
  (start))
