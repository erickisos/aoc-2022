(ns aoc.days.02
  (:require [aoc.file :refer [load!]]
            [clojure.string :as str]))

(def IN {"A" :rock
         "B" :paper
         "C" :scissors})
(def ANS {"X" :rock
          "Y" :paper
          "Z" :scissors})
(def STATUS-POINTS {:win  6
                    :draw 3
                    :lose 0})
(def SELECTION-POINTS {:rock     1
                       :paper    2
                       :scissors 3})
(def CONDITIONS
  {:rock     {:paper    :win
              :scissors :lose
              :rock     :draw}
   :paper    {:paper    :draw
              :scissors :win
              :rock     :lose}
   :scissors {:paper    :lose
              :scissors :draw
              :rock     :win}})

(defn status
  [opponent you]
  (get-in CONDITIONS [opponent you]))

(defn with-points
  [{:keys [status selection] :as turn}]
  (assoc turn :points (+ (status STATUS-POINTS)
                         (selection SELECTION-POINTS))))

(defn ->turn
  [row]
  (let [[opponent you] (str/split row #" ")
        opponent (get IN opponent)
        you      (get ANS you)
        turn {:status (status opponent you)
              :selection you}]
    (with-points turn)))


(defn part-01
  [turns]
  (apply + (map :points turns)))

(defn main
  [filename]
  (let [turns (map ->turn (load! filename))]
    {:part-01 (part-01 turns)}))

