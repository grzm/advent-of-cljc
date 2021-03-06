(ns com.grzm.advent-of-code.advent-2017.day-10.part-2
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-10.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-10.part-1 :as part-1]
   #?(:clj [criterium.core :refer [quick-bench]])))

(defn char-code [c]
  #?(:clj (int c)
     :cljs (.charCodeAt c 0)))

(defn parse [s]
  (map char-code s))

(defn parse+ [s]
  (concat (parse s) [17, 31, 73, 47, 23]))

(defn sparse-hash [llen lens rounds]
  (loop [rnd 0
         state (part-1/init-state llen)]
    (if (< rnd rounds)
      (recur (inc rnd)
             (reduce part-1/hashish state lens))
      (:st state))))

#?(:clj
   (def hex-format-pattern (apply str (repeat 16 "%02x"))))

#?(:cljs
   (defn hex-format-1 [x]
     (let [x-str (.toString x 16)]
       (if (= 2 (count x-str))
         x-str
         (str "0" x-str)))))

(defn hex-format [xs]
  #?(:clj
     (apply format hex-format-pattern xs)
     :cljs
     (apply str (map hex-format-1 xs))))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (let [lens (parse+ input)
         sh (sparse-hash 256 lens 64)]
     (->> sh
          (partition 16)
          (map #(apply bit-xor %))
          hex-format))))

(comment
  (solve)
  (quick-bench (solve))
  ;; Evaluation count : 6 in 6 samples of 1 calls.
  ;; Execution time mean : 213.528544 ms
  ;; Execution time std-deviation : 11.776479 ms
  ;; Execution time lower quantile : 198.027305 ms ( 2.5%)
  ;; Execution time upper quantile : 225.176068 ms (97.5%)
  ;; Overhead used : 2.241341 ns
  )
