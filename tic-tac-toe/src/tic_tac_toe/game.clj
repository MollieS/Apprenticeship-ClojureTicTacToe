(ns tic-tac-toe.game
  (:require [tic-tac-toe.validating-prompt :as prompt]
            [tic-tac-toe.writer :as writer]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.marks :as marks]))

(defn- empty-board []
(board/create-empty-board))

(defn- has-win? [board]
  (board/winning-line? board))

(defn- announce-win [board]
  (writer/win (board/winning-symbol board)))

(defn- no-free-spaces? [board]
(not (board/free-spaces? board)))

(defn play-move [board]
  (let [updated-board (board/place-mark board (marks/next-mark board) (prompt/valid-next-move board) )]
    (writer/display updated-board)
    (cond
      (has-win? updated-board) (announce-win updated-board)
      (no-free-spaces? updated-board) (writer/draw)
      :else
       (play-move updated-board)
      )
    )
  )

(defn start[]
  (let [board (empty-board)]
    (writer/display board)
    (play-move board)))

(defn -main[]
  (start))
