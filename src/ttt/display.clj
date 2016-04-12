(ns ttt.display
  (require [clojure.string :as str]))

(defn spot->str [spot]
  (if (empty? spot)
    "___"
    (format "_%s_" spot)))

(defn row->str [row]
  (let [spots (map spot->str row)]
    (format "|%s|%s|%s|" (first spots) (second spots) (nth spots 2))))

(defn board->str [board]
  (let [rows (partition 3 board)]
    (str/join "\n" (map row->str rows))))

