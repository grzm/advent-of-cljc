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

(defn solve-cube
  ([]
   (solve data/input))
  ([input]
   (->> (part-1/parse input)
        (reductions part-1/cube-pos part-1/cube-origin)
        (reduce (fn [max-mag move]
                  (let [curr-mag (part-1/cube-manhattan-distance move)]
                    (max max-mag curr-mag)))
                0))))

(defn solve-cube-max
  ([]
   (solve data/input))
  ([input]
   (->> (part-1/parse input)
        (reductions part-1/cube-pos part-1/cube-origin)
        (map part-1/cube-manhattan-distance)
        max)))


(comment
  (solve)
  (quick-bench (solve)) ;; find-min-2
  ;; Evaluation count : 6 in 6 samples of 1 calls.
  ;; Execution time mean : 367.992557 ms
  ;; Execution time std-deviation : 10.686969 ms
  ;; Execution time lower quantile : 352.750746 ms ( 2.5%)
  ;; Execution time upper quantile : 378.562289 ms (97.5%)
  ;; Overhead used : 2.241341 ns

  (quick-bench (solve)) ;; find-min-2-trans
  ;; Evaluation count : 6 in 6 samples of 1 calls.
  ;; Execution time mean : 365.221857 ms
  ;; Execution time std-deviation : 11.296257 ms
  ;; Execution time lower quantile : 350.511902 ms ( 2.5%)
  ;; Execution time upper quantile : 379.225066 ms (97.5%)
  ;; Overhead used : 2.241341 ns

  (quick-bench (solve-cube))
  ;; Evaluation count : 6 in 6 samples of 1 calls.
  ;; Execution time mean : 388.332339 ms
  ;; Execution time std-deviation : 15.524320 ms
  ;; Execution time lower quantile : 372.687807 ms ( 2.5%)
  ;; Execution time upper quantile : 410.351894 ms (97.5%)
  ;; Overhead used : 2.241341 ns

  (quick-bench (solve-cube-max))nil

  )
