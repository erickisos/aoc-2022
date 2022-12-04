(ns aoc.main
  (:require [aoc.elves :refer [safe-max segment sum-calories]]
            [aoc.file :refer [load!]]))


(defn day-01
  [filename]
  (->> filename
       load!
       segment
       (map sum-calories)
       safe-max))

(def days {1 {:fn    day-01
              :input "01D-01.txt"}})

(defn main
  [day]
  (let [current   (get days day)
        day-fn    (:fn current)
        day-input (:input current)]
    (day-fn day-input)))
