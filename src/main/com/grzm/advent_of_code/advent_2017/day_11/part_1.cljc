(ns com.grzm.advent-of-code.advent-2017.day-11.part-1
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-11.data :as data]
   #?(:clj [criterium.core :refer [quick-bench]])))

;; n, ne, nw are pos
;; s, se, sw are neg

(defn parse [s]
  (re-seq #"\w+" s))

(defn normalized-move [move]
  (case move
    "n" [:n inc]
    "s" [:n dec]
    "ne" [:ne inc]
    "se" [:nw dec]
    "nw" [:nw inc]
    "sw" [:ne dec]))

(defn mag [m]
  (apply + (map #(Math/abs %) (vals m))))

(defn xf [m i]
  (-> m
      (update :n #(+ % i))
      (update :ne #(- % i))
      (update :nw #(- % i))))

;; brute force to find minimum
;; there *must* be a simpler way of calculating this
(defn find-min
  ([m]
   (let [mag-m (mag m)
         m' (xf m 1)
         mag-m' (mag m')]
     (if (< mag-m' mag-m)
       (find-min m' mag-m' 1)
       (let [m' (xf m -1)
             mag-m' (mag m')]
         (if (< mag-m' mag-m)
           (find-min m' mag-m' -1)
           [m mag-m])))))
  ([m mag-m i]
   (loop [m m, mag-m mag-m]
     (let [m' (xf m i)
           mag-m' (mag m')]
       (if (< mag-m' mag-m)
         (recur m' mag-m')
         [m mag-m])))))

(defn xf- [m i]
  (-> m
      (update :n #(- % i))
      (update :ne #(+ % i))
      (update :nw #(+ % i))))

(defn find-min-2 [m]
  (->> (map vector [1 -1 -1] ((juxt :n :ne :nw) m))
       (map (partial apply *))
       (map (partial xf- m))
       (map #(vector % (mag %)))
       (sort-by second)
       first))

(defn update-move [moves move]
  (let [[move' op] (normalized-move move)]
    (update moves move' op)))

(defn solve*
  [moves]
  (->> moves
       (reduce update-move {:n 0, :ne 0, :nw 0})
       find-min-2))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (->> (parse input)
        solve*
        first
        mag)))

(comment
  (solve) ;; {:n 41, :nw -369, :ne 449} ;; 859

  (find-min {:n 41, :nw -369, :ne 449})[{:n 0, :nw -328, :ne 490} 818]
  ;; -41
  ;; 0 = x + y + z
  ;; 0 = 41 + 449 - 369
  ;; minimize |x| + |y| + |z|

  (def m {:n 41, :nw -369, :ne 449})
  (mag (xf m -41)) ;; 818
  (mag (xf m -369)) ;; 1146
  (mag (xf m 449)) ;; 1308

  (def m {:n -2, :ne 2, :nw 0})
  (mag (xf m 2)) ;; 2

  (def m {:n 0, :ne -3, :nw -2})
  (mag m) ;; 5
  (mag (xf m -3)) ;; 4
  (mag (xf m -2)) ;; 3
  (xf m -2) ;; {:n -2, :ne -1, :nw 0}


  (mag m) ;; 859
  (mag (xf m 1)) ;; 860
  (mag (xf m -1)) ;; 858

  (find-min {:n -2, :ne 2, :nw 0})[{:n 0, :ne 0, :nw -2} 2]
  {:n -2 :ne 2 :nw 0}
  {:n 1 :ne 0 :nw 0}
  {:n 0 :ne -1 :nw 0}

  (find-min-2 {:n -2, :ne 2, :nw 0})

  (find-min m)
  (solve) ;; [{:n 41, :nw -369, :ne 449} 1596]
  (quick-bench (solve))
  ;; Evaluation count : 126 in 6 samples of 21 calls.
  ;; Execution time mean : 5.325416 ms
  ;; Execution time std-deviation : 523.518056 µs
  ;; Execution time lower quantile : 4.527747 ms ( 2.5%)
  ;; Execution time upper quantile : 5.838837 ms (97.5%)
  ;; Overhead used : 2.241341 ns
  )
