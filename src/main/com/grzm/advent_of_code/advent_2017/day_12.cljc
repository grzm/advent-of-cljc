(ns com.grzm.advent-of-code.advent-2017.day-12
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-12.data :as data]
   [com.grzm.advent-of-code.advent-2017.util :as util]
   #?(:clj [criterium.core :refer [quick-bench]])))

(defn parse-line
  [line]
  (let [[prog _ & progs] (str/split (str/trim line) #" ")]
    [(util/parse-int prog)
     (mapv (comp util/parse-int #(re-find #"\d+" %)) progs)]))

(defn parse
  "Returns a map of prog-id to vector of connected progs"
  [input]
  (->> (str/split-lines input)
       (map parse-line)
       (into {})))

(defn prog-group [n pipes]
  (let [branch?  (constantly true)
        seen     (atom #{n})
        children #(let [p   (pipes %)
                        new (remove @seen p)]
                    (swap! seen set/union (set p))
                    new)]
    (set (tree-seq branch? children n))))

(defn part-1
  ([]
   (part-1 data/input))
  ([input]
   (->> (parse input)
        (prog-group 0)
        count)))

(defn part-2
  ([]
   (part-2 data/input))
  ([input]
   (let [pipes (parse input)]
     (loop [[id & r-ids :as ids] (keys pipes)
            seen                 #{}
            groups               #{}]
       (if (seq ids)
         (let [g     (prog-group id pipes)
               seen' (set/union seen g)]
           (recur (remove seen' r-ids)
                  seen'
                  (conj groups g)))
         (count groups))))))

(comment
  (part-1) ;; 378
  (part-2 sample-input)
  (part-2 data/input)

  (quick-bench (part-1))
  ;; Evaluation count : 108 in 6 samples of 18 calls.
  ;; Execution time mean : 5.957916 ms
  ;; Execution time std-deviation : 555.600767 µs
  ;; Execution time lower quantile : 5.387437 ms ( 2.5%)
  ;; Execution time upper quantile : 6.527482 ms (97.5%)
  ;; Overhead used : 1.870143 ns

  (quick-bench (part-2))
  ;; Evaluation count : 108 in 6 samples of 18 calls.
  ;; Execution time mean : 5.738449 ms
  ;; Execution time std-deviation : 225.077823 µs
  ;; Execution time lower quantile : 5.429169 ms ( 2.5%)
  ;; Execution time upper quantile : 5.955487 ms (97.5%)
  ;; Overhead used : 1.870143 ns

  (def sample-input "0 <-> 2
  1 <-> 1
  2 <-> 0, 3, 4
  3 <-> 2, 4
  4 <-> 2, 3, 6
  5 <-> 6
  6 <-> 4, 5")

  (parse sample-input)
  ;; both directions, so we can get cycles :/
  (let [pipes    (parse sample-input)
        branch?  (constantly true)
        seen     (atom #{0})
        children #(let [p   (pipes %)
                        new (remove @seen p)]
                    (swap! seen set/union (set p))
                    new)]
    (tree-seq branch? children 0))
  )
