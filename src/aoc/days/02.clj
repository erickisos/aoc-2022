(ns aoc.days.02
  (:require [aoc.file :refer [load!]]
            [clojure.string :as str]))

(def IN {"A" :rock
         "B" :paper
         "C" :scissors})
(def ANS {"X" :rock
          "Y" :paper
          "Z" :scissors})
(def ANS-END {"X" :lose
              "Y" :draw
              "Z" :win})
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
(def CONDITIONS-BY-RES
  {:rock     {:win  :paper
              :draw :rock
              :lose :scissors}
   :scissors {:win  :rock
              :draw :scissors
              :lose :paper}
   :paper    {:win  :scissors
              :draw :paper
              :lose :rock}})

(defn status
  [opponent you]
  (get-in CONDITIONS [opponent you]))

(defn with-points
  [{:keys [status selection] :as turn}]
  (assoc turn :points (+ (status STATUS-POINTS)
                         (selection SELECTION-POINTS))))

(defn select-from-ans
  [selection _opponent]
  (get ANS selection))

(defn select-from-res
  [selection opponent]
  (get-in CONDITIONS-BY-RES [opponent (get ANS-END selection)]))

(defn ->simple-turn
  [row your-selector]
  (let [[opponent-sel your-mov] (str/split row #" ")
        opponent (get IN opponent-sel)
        you      (your-selector your-mov opponent)
        turn     {:status    (status opponent you)
                  :selection you}]
    (with-points turn)))

(defn part-01
  [turns]
  (->> turns
       (map #(->simple-turn % select-from-ans))
       (map :points)
       (apply +)))

(defn part-02
  [turns]
  (->> turns
       (map #(->simple-turn % select-from-res))
       (map :points)
       (apply +)))

(defn main
  [filename]
  (let [turns (load! filename)]
    {:part-01 (part-01 turns)
     :part-02 (part-02 turns)}))
