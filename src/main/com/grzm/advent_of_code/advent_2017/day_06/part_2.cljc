(ns com.grzm.advent-of-code.advent-2017.day-06.part-2
  (:require
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-06.core :as core]
   [com.grzm.advent-of-code.advent-2017.day-06.data :as data]))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (loop [steps 0
          seen {}
          locs input]
     (if-let [seen-steps (seen locs)]
       (- steps seen-steps)
       (recur (inc steps)
              (conj seen {locs steps})
              (core/redistribute locs))))))
(comment
  (solve)

  )
