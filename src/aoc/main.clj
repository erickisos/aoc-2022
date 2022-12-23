(ns aoc.main
  (:require [aoc.days.01 :as aoc.day-01]
            [aoc.days.02 :as aoc.day-02]
            [aoc.days.03 :as aoc.day-03]
            [clojure.tools.cli :refer [parse-opts]]))


(def cli-options
  [["-d" nil "Aoc Day"
    :id :day
    :default 1]
   ["-h" "--help"]])

(def days {1 {:fn    aoc.day-01/main
              :input "01D-01.txt"}
           2 {:fn    aoc.day-02/main
              :input "02D-01.txt"}
           3 {:fn aoc.day-03/main
              :input "03D-01.txt"}})

(defn -main
  [& args]
  (let [{:keys [options summary]} (parse-opts args cli-options)
        {:keys [fn input]}   (get days (:day options))]
    (if (:help options)
      (println summary)
      (fn input))))
