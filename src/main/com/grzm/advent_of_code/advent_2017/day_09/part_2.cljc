(ns com.grzm.advent-of-code.advent-2017.day-09.part-2
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-09.core :as core]
   [com.grzm.advent-of-code.advent-2017.day-09.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-09.part-1 :as part-1]
   #?(:clj [criterium.core :refer [quick-bench]])))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (solve {:state :init} input))
  ([init-state input]
   (:garbage (part-1/solve* init-state input))))

(comment

  (solve)
  (quick-bench (solve))
  ;; Evaluation count : 90 in 6 samples of 15 calls.
  ;; Execution time mean : 7.536217 ms
  ;; Execution time std-deviation : 1.035529 ms
  ;; Execution time lower quantile : 6.335756 ms ( 2.5%)
  ;; Execution time upper quantile : 8.943154 ms (97.5%)
  ;; Overhead used : 2.241341 ns

  )
