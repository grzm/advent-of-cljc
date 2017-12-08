(ns com.grzm.advent-of-code.advent-2017.day-08.part-2
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-08.core :as core]
   [com.grzm.advent-of-code.advent-2017.day-08.data :as data]))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (->> (reductions core/op {} (core/parse input))
        (keep vals)
        flatten
        (apply max))))

(comment
  (def sample
    "b inc 5 if a > 1
  a inc 1 if b < 5
  c dec -10 if a >= 1
  c inc -20 if c == 10")

  (solve sample)
  (solve)

  )
