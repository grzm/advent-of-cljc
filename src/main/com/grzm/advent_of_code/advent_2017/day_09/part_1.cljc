(ns com.grzm.advent-of-code.advent-2017.day-09.part-1
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-09.core :as core]
   [com.grzm.advent-of-code.advent-2017.day-09.data :as data]
   #?(:clj [criterium.core :refer [quick-bench]])))

(defn solve-condp
  [init-state input]
  (reduce core/p-condp init-state (seq input)))

(defn solve-case
  [init-state input]
  (reduce core/p-case init-state (seq input)))

(defn solve-all-case
  [init-state input]
  (reduce core/p-case init-state (seq input)))

(def solve* solve-case)

(defn solve
  ([]
   (solve data/input))
  ([input]
   (solve {:state :init} input))
  ([init-state input]
   (:count (solve* init-state input))))

(comment
  (solve)
  (quick-bench (solve))

  (quick-bench (solve-condp {:state :init} data/input))
  ;; Evaluation count : 72 in 6 samples of 12 calls.
  ;; Execution time mean : 9.033702 ms
  ;; Execution time std-deviation : 387.316276 µs
  ;; Execution time lower quantile : 8.507198 ms ( 2.5%)
  ;; Execution time upper quantile : 9.429824 ms (97.5%)
  ;; Overhead used : 2.241341 ns

  (quick-bench (solve-case {:state :init} data/input))
  ;; Evaluation count : 90 in 6 samples of 15 calls.
  ;; Execution time mean : 7.170632 ms
  ;; Execution time std-deviation : 136.371703 µs
  ;; Execution time lower quantile : 7.021631 ms ( 2.5%)
  ;; Execution time upper quantile : 7.352099 ms (97.5%)
  ;; Overhead used : 2.241341 ns

  (quick-bench (solve-all-case {:state :init} data/input))
  ;; Evaluation count : 90 in 6 samples of 15 calls.
  ;; Execution time mean : 8.008189 ms
  ;; Execution time std-deviation : 1.091521 ms
  ;; Execution time lower quantile : 6.904067 ms ( 2.5%)
  ;; Execution time upper quantile : 9.228034 ms (97.5%)
  ;; Overhead used : 2.241341 ns


  )
