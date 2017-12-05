(ns com.grzm.advent-of-code.advent-2017.day-05.part-1
  (:require
   [com.grzm.advent-of-code.advent-2017.day-05.core :as core]))

(defn jump
  [i instructions]
  (let [i' (get instructions i)]
    [(+ i i') (assoc instructions i (inc i))]))

(def solve (core/solver jump))

(comment
  (solve) ;; 326618
  )
