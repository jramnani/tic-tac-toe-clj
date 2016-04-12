(ns ttt.display
  (require [clojure.string :as str]))

(defn spot->str [spot]
  (let [index (first spot)
        player (second spot)]
    (if (empty? player)
      (format "_%s_" index)
      (format "_%s_" player))))

(defn row->str [row]
  (let [spots (map spot->str row)]
    (if (empty? spots)
      ""
    (format "|%s|%s|%s|" (first spots) (second spots) (nth spots 2)))))

(defn board->str [board]
  (let [spots-with-indicies (keep-indexed #(vec [%1 %2]) board)
        rows (partition 3 spots-with-indicies)]
    (str/join "\n" (map row->str rows))))

