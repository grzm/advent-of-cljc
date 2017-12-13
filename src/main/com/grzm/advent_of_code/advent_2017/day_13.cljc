(ns com.grzm.advent-of-code.advent-2017.day-13
  (:require
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-13.data :as data]
   [com.grzm.advent-of-code.advent-2017.util :as util]
   #?(:clj [criterium.core :refer [quick-bench]])))

(defn parse-line
  [line]
  (let [vals (str/split (str/trim line) #": ")]
    (mapv util/parse-int vals)))

(defn parse
  "Returns a sequence of [depth range] vectors"
  [input]
  (->> (str/split-lines input)
       (mapv parse-line)))

(defn range-cycle* [r]
  (concat (range r) (reverse (range 1 (dec r)))))

(defn damage-at-fn [[d r]]
  (let [len    (count (range-cycle* r))
        damage (* d r)]
    (fn [t]
      (if (zero? (mod (+ t d) len)) damage 0))))

(defn damage-at-t [d-rs]
  (->> (map damage-at-fn d-rs)
       (apply juxt)))

(defn part-1
  ([]
   (part-1 data/input))
  ([input]
   (let [damage-at (->> (parse input)
                        damage-at-t)]
     (apply + (damage-at 0)))))

(defn pos-at-fn [[d r]]
  (let [rc  (vec (range-cycle* r))
        len (count rc)]
    (fn [t] (rc (mod (+ t d) len)))))

(defn pos-at-t [d-rs]
  (->> (map pos-at-fn d-rs)
       (apply juxt)))

(defn part-2
  ([]
   (part-2 data/input))
  ([input]
   (let [pos-at (pos-at-t (parse input))]
     (->> (range)
          (drop-while #(some zero? (pos-at %)))
          first))))

(defn home?-fn [[d r]]
  (let [len (- (* 2 r) 2)]
    #(zero? (mod (+ % d) len))))

(defn part-2*
  ([]
   (part-2* data/input))
  ([input]
   (let [home?s        (map home?-fn (parse input))
         some-at-home? (apply some-fn home?s)]
     (->> (range)
          (drop-while some-at-home?)
          first))))

(comment

  (def sample-input "0: 3
  1: 2
  4: 4
  6: 4")

  (part-1 sample-input)
  (part-1)

  (let [home? (home?-fn [0 3])]
    (->> (range)
         (map #(vector % (home? %)))
         (take 12)))

  (part-2 sample-input)
  (part-2)
  (quick-bench (part-2))
  ;; Evaluation count : 6 in 6 samples of 1 calls.
  ;; Execution time mean : 21.458790 sec
  ;; Execution time std-deviation : 2.600161 sec
  ;; Execution time lower quantile : 18.780047 sec ( 2.5%)
  ;; Execution time upper quantile : 24.396993 sec (97.5%)
  ;; Overhead used : 2.219741 ns

  (part-2*)
  (quick-bench (part-2*))
  ;; Evaluation count : 6 in 6 samples of 1 calls.
  ;; Execution time mean : 887.239963 ms
  ;; Execution time std-deviation : 34.408776 ms
  ;; Execution time lower quantile : 858.532278 ms ( 2.5%)
  ;; Execution time upper quantile : 928.335587 ms (97.5%)
  ;; Overhead used : 2.219741 ns

  )
