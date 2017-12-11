(ns com.grzm.advent-of-code.advent-2017.day-11.part-2
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-11.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-11.part-1 :as part-1]))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (->> (part-1/parse input)
        (reductions part-1/update-move {:n 0 :ne 0 :nw 0})
        (reduce (fn [max-mag move]
                  (let [curr-mag (second (part-1/find-min move))]
                    (max max-mag curr-mag)))
                0))))

(comment
  (solve)
  )
