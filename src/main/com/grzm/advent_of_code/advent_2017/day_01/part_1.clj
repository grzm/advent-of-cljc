(ns com.grzm.advent-of-code.advent-2017.day-01.part-1
  (:require
   [com.grzm.advent-of-code.advent-2017.day-01 :as day-01]))

(defn solve
  [xs]
  (->> (concat (partition 2 1 xs)
               (list (list (last xs) (first xs))))
       (transduce day-01/x-form +)))
