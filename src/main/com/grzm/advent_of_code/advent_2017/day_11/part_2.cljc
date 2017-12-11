(ns com.grzm.advent-of-code.advent-2017.day-11.part-2
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-11.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-11.part-1 :as part-1]
   #?(:clj [criterium.core :refer [quick-bench]])))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (->> (part-1/parse input)
        (reductions part-1/update-move {:n 0 :ne 0 :nw 0})
        (reduce (fn [max-mag move]
                  (let [curr-mag (second (part-1/find-min-2 move))]
                    (max max-mag curr-mag)))
                0))))

(comment
  (solve)
  (quick-bench (solve))
  ;; Evaluation count : 6 in 6 samples of 1 calls.
  ;; Execution time mean : 367.992557 ms
  ;; Execution time std-deviation : 10.686969 ms
  ;; Execution time lower quantile : 352.750746 ms ( 2.5%)
  ;; Execution time upper quantile : 378.562289 ms (97.5%)
  ;; Overhead used : 2.241341 ns  
  )
