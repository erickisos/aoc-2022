(ns aoc.days.02
  (:require [aoc.file :refer [load!]]
            [clojure.set :as set]
            [clojure.string :as str]))

(defn with-points
  [{:keys [status selection] :as turn}]
  (assoc turn :points (+ (get {:win 6 :draw 3 :lose 0} status)
                         (get {:rock 1 :paper 2 :scissors 3} selection))))

(defn count-points
  [turns]
  (apply + (map :points (map with-points turns))))

(defn build-turn
  [input key conditions options]
  (let [[opponent-str selected-str] (str/split input #" ")
        opponent-k    (get {"A" :rock "B" :paper "C" :scissors} opponent-str)
        selected-opt  (get options selected-str)
        secondary-opt (get-in conditions [opponent-k selected-opt])
        results       (cond-> [selected-opt secondary-opt] (= key :selection) reverse)]
    (zipmap [:status :selection] results)))

(defn build-and-count
  [turns key conditions options]
  (count-points (map #(build-turn % key conditions options) turns)))

(defn main
  [filename]
  (let [turns (load! filename)
        conditions {:rock     {:paper    :win
                               :scissors :lose
                               :rock     :draw}
                    :paper    {:paper    :draw
                               :scissors :win
                               :rock     :lose}
                    :scissors {:paper    :lose
                               :scissors :draw
                               :rock     :win}}
        cond-by-res (update-vals conditions set/map-invert)]
    {:part-01 (build-and-count turns :selection conditions {"X" :rock "Y" :paper "Z" :scissors})
     :part-02 (build-and-count turns :status cond-by-res {"X" :lose "Y" :draw "Z" :win})}))
