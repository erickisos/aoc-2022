(ns aoc.main
  (:require [aoc.elves :refer [segment total-calories]]
            [aoc.file :refer [load!]]))


(defn day-01
  []
  (->> "01D-01.txt"
       load!
       segment
       (map total-calories)
       (sort >)
       (take 3)))

(def days {:day-01 day-01})

(defn main
  [day]
  (let [day-fn (get days day)]
    (day-fn)))

