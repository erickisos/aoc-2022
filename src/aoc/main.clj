(ns aoc.main
  (:require [aoc.days.01 :as aoc.day-01]
            [aoc.days.02 :as aoc.day-02]))


(def days {1 {:fn    aoc.day-01/main
              :input "01D-01.txt"}
           2 {:fn aoc.day-02/main}})

(defn main
  [day]
  (let [current   (get days day)
        day-fn    (:fn current)
        day-filename (:input current)]
    (day-fn day-filename)))

(main 1)
