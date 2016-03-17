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

(defn- no-free-spaces? [board]
  (not (board/free-spaces? board)))

(defn play-single-move [players board mark]
  (board/place-mark board mark
                    ((get players mark) board) )
  )

(defn announce-win [board]
  (writer/display board)
  (writer/win-message (board/winning-symbol board)))

(defn announce-draw [board]
  (writer/display board)
  (writer/draw-message))

(defn play-move [board players]
  (let [next-mark (marks/next-mark board)
        updated-board (play-single-move players board next-mark)]
    (cond
      (has-win? updated-board) (announce-win updated-board)
      (no-free-spaces? updated-board) (announce-draw updated-board)
      :else
       (play-move updated-board players)
      )
    )
  )

(defn start[]
  (let [player-choice (prompt/get-valid-player-option)
        board (empty-board)]
    (play-move board (players/configure-players player-choice))))

(defn -main[]
  (start))
