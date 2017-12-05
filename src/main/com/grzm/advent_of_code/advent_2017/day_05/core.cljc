(ns com.grzm.advent-of-code.advent-2017.day-05.core
  (:require [com.grzm.advent-of-code.advent-2017.day-05.data :as data]))

(defn solver [jump]
  (fn solve
    ([]
     (solve data/input))
    ([input]
     (let [instruction-count (count input)]
       (loop [c            0
              i            0
              instructions input]
         (if (or (neg? i) (<= instruction-count i))
           c
           (let [[i' instructions'] (jump i instructions)]
             (recur (inc c) i' instructions'))))))))
