(ns aoc.days.01
  (:require [aoc.file :refer [load!]]))

(defn part-01
  [elves-calories]
  (apply max elves-calories))

(defn part-02
  [elves-calories]
  (apply + (take 3 (sort > elves-calories))))

(defn main
  [filename]
  (let [foods (map parse-long (load! filename))
        elves (partition-by nil? foods)
        totals (filter #(not (nil? %)) (map #(apply + %) elves))]
    {:part-01 (part-01 totals)
     :part-02 (part-02 totals)}))
