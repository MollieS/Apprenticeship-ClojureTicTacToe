(ns tic-tac-toe.game

  (:require [tic-tac-toe.validating-prompt :as prompt]
            [tic-tac-toe.writer :as writer]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.marks :as marks]
            [tic-tac-toe.players :as players]))

(defn- empty-board []
(board/create-empty-board))

(defn- has-win? [board]
  (board/winning-line? board))

(defn- announce-win [board]
  (writer/win-message (board/winning-symbol board)))

(defn- no-free-spaces? [board]
(not (board/free-spaces? board)))

(defn play-move [board players]
  (let [next-mark (marks/next-mark board)
        updated-board (board/place-mark board next-mark
                                        ((get players next-mark) board) )]
    (writer/display updated-board)
    (cond
      (has-win? updated-board) (announce-win updated-board)
      (no-free-spaces? updated-board) (writer/draw-message)
      :else
       (play-move updated-board players)
      )
    )
  )

(defn start[]
  (let [player-choice (prompt/get-valid-player-option)
        board (empty-board)]
    (writer/display board)
    (play-move board (players/configure-players player-choice))))

(defn -main[]
  (start))
