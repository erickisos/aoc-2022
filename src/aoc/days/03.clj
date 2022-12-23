(ns aoc.days.03
  (:require [aoc.file :refer [load!]]
            [clojure.set :as set]))

(defn item->priority
  [item]
  (let [threshold (if (Character/isUpperCase item) 38 96)]
    (- (int item) threshold)))

(defn calc-priorities
  [items]
  (->> items
       (map set)
       (apply set/intersection)
       (map item->priority)))

(defn sum-priorities
  [items]
  (reduce + (flatten (map calc-priorities items))))

(defn part-01
  [rucksacks]
  (let [divide #(split-at (/ (count %) 2) %)]
    (map divide rucksacks)))

(defn part-02
  [rucksacks]
  (partition 3 rucksacks))

(defn main
  [filename]
  (let [rucksacks (load! filename)]
    {:part-01 (sum-priorities (part-01 rucksacks))
     :part-02 (sum-priorities (part-02 rucksacks))}))

