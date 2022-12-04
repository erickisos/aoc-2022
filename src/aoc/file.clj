(ns aoc.file
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn load!
  [filename]
  (str/split-lines (slurp (io/resource filename))))
