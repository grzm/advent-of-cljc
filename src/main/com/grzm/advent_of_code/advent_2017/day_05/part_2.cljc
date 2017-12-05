(ns com.grzm.advent-of-code.advent-2017.day-05.part-2
  (:require
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-05.core :as core]))

(defn jump [i instructions]
  (let [i' (get instructions i)]
    [(+ i i') (assoc instructions i (if (< 2 i') (dec i') (inc i')))]))

(def solve (core/solver jump))

(comment
  (solve);; 21841249
  )
