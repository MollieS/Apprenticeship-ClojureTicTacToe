(ns tic-tac-toe.delayed-player
(:require [tic-tac-toe.board :as board]))

(def ^:dynamic sleep #(Thread/sleep %))

(defn delayed-move [interval player board]
 (sleep interval)
 (player board))
