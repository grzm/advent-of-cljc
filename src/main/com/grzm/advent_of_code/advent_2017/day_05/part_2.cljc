(ns com.grzm.advent-of-code.advent-2017.day-05.part-2
  (:require
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-05.core :as core]
   [com.grzm.advent-of-code.advent-2017.day-05.data :as data]
   #?(:clj[criterium.core :refer [quick-bench]])))

(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)

(defn jump [i instructions]
  (let [i' ^Integer (get instructions i)]
    [(+ i i') (assoc instructions i (if (< 2 i') (dec i') (inc i')))]))

(def solve (core/solver jump))

;; optimize loop using suggestions from borkdude
;; https://github.com/borkdude/aoc2017/blob/master/src/day5.clj
(defn solve-j
  ([]
   (solve-j data/input))
  ([input]
   (let [^ints maze (into-array Integer/TYPE input)
         length     ^int (alength maze)]
     (loop [cur-pos 0
            steps   0]
       (if (< cur-pos length)
         (let [cur-val (aget maze cur-pos)]
           (if (zero? cur-val)
             (do
               (aset maze cur-pos 2)
               (recur (inc cur-pos)
                      (+ steps 2)))
             (do (aset maze cur-pos (if (>= cur-val 3)
                                      (dec cur-val)
                                      (inc cur-val)))
                 (recur (+ cur-pos cur-val)
                        (inc steps)))))
         steps)))))

(defn ^Integer expr [^Integer cur-val]
  (if (>= cur-val 3)
    (dec cur-val)
    (inc cur-val)))

(defn solver-j
  [expr]
  (fn solver
    ([]
     (solver data/input))
    ([input]
     (let [^ints maze (into-array Integer/TYPE input)
           length     ^int (alength maze)]
       (loop [cur-pos 0
              steps   0]
         (if (< cur-pos length)
           (let [cur-val (aget maze cur-pos)]
             (if (zero? cur-val)
               (do
                 (aset maze cur-pos 2)
                 (recur (inc cur-pos)
                        (+ steps 2)))
               (do (aset maze cur-pos ^Integer (expr cur-val) )
                   (recur (+ cur-pos cur-val)
                          (inc steps)))))
           steps))))))

(def solve-j-2 (solver-j expr))

(comment
  (solve);; 21841249
  ;; => 21841249
  (solve-j)
  ;; => 21841249

  (solve-j-2);; => 21841249


  (quick-bench (solve-j-2))
  ;; Evaluation count : 6 in 6 samples of 1 calls.
  ;;              Execution time mean : 651.776829 ms
  ;;     Execution time std-deviation : 101.636795 ms
  ;;    Execution time lower quantile : 582.981577 ms ( 2.5%)
  ;;    Execution time upper quantile : 824.601821 ms (97.5%)
  ;;                    Overhead used : 2.039653 ns

  ;; Found 1 outliers in 6 samples (16.6667 %)
  ;;    low-severe       1 (16.6667 %)
  ;;  Variance from outliers : 47.3227 % Variance is moderately inflated by outliers

  (time (solve)) ;; "Elapsed time: 7933.240182 msecs"

  (time (solve-j))
  (quick-bench (solve))
  ;; Evaluation count : 6 in 6 samples of 1 calls.
  ;;             Execution time mean : 6.124387 sec
  ;;    Execution time std-deviation : 633.816599 ms
  ;;   Execution time lower quantile : 5.379325 sec ( 2.5%)
  ;;   Execution time upper quantile : 6.883021 sec (97.5%)
  ;;   Overhead used : 2.039653 ns

  (quick-bench (solve-j))
  ;; Evaluation count : 12 in 6 samples of 2 calls.
  ;;             Execution time mean : 104.618357 ms
  ;;    Execution time std-deviation : 9.633911 ms
  ;;   Execution time lower quantile : 89.872700 ms ( 2.5%)
  ;;   Execution time upper quantile : 113.298396 ms (97.5%)
  ;;   Overhead used : 2.039653 ns

  )
