(ns com.grzm.advent-of-code.advent-2017.day-06.part-1
  (:require
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-06.core :as core]
   [com.grzm.advent-of-code.advent-2017.day-06.data :as data]))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (loop [steps 0
          seen  #{}
          locs  input]
     (if (seen locs)
       steps
       (recur (inc steps)
              (conj seen locs)
              (core/redistribute locs))))))

(comment
  (solve)
  )
