(ns aoc.days.03
  (:require [aoc.file :refer [load!]]
            [clojure.set :as set]))

(defn item->priority
  [item]
  (let [threshold (if (Character/isUpperCase item) 38 96)]
    (- (int item) threshold)))

(defn- calc-priorities
  [rs]
  (let [perfect-half (/ (count rs) 2)]
    (->> (split-at perfect-half rs)
         (map set)
         (apply set/intersection)
         (map item->priority))))

(defn part-01
  [rucksacks]
  (->> rucksacks
       (map calc-priorities)
       flatten
       (reduce +)))

(defn main
  [filename]
  (let [rucksacks (load! filename)]
    {:part-01 (part-01 rucksacks)}))
