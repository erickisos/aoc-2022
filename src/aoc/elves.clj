(ns aoc.elves)

(defn segment
  [separated-foods]
  (->> separated-foods
       (map parse-long)
       (partition-by nil?)))

(defn sum-calories
  [foods]
  (apply + foods))

(defn safe-max
  [total-calories]
  (->> total-calories
       (filter #(not (nil? %)))
       (apply max)))
