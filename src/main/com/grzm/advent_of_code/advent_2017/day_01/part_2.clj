(ns com.grzm.advent-of-code.advent-2017.day-01.part-2
  (:require [com.grzm.advent-of-code.advent-2017.day-01 :as day-01]))

(defn solve
  [xs]
  (let [r (/ (count xs) 2)]
    (->> (map vector xs (concat (nthrest xs (/ (count xs) 2)) xs))
         (transduce day-01/x-form +))))
