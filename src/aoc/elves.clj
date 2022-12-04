(ns aoc.elves)

(defn segment
  [separated-foods]
  (->> separated-foods
       (map parse-long)
       (partition-by nil?)
       (map #(filter int? %))
       (filter seq)))

(defn total-calories
  [foods]
  (reduce + foods))
