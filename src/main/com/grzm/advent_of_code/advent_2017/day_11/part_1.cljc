(ns com.grzm.advent-of-code.advent-2017.day-11.part-1
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-11.data :as data]))

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

(defn update-move [moves move]
  (let [[move' op] (normalized-move move)]
    (update moves move' op)))

(defn solve*
  [moves]
  (->> moves
       (reduce update-move {:n 0, :ne 0, :nw 0})
       find-min))

#_(defn solve*
  [moves]
  (reduce (fn [[moves-map max-mag] move]
            (let [[move' op] (normalized-move move)
                  map' (update moves-map move' op)
                  ;;[_ curr-mag] (find-min map')
                  ]
              [map' 0;;(max curr-mag max-mag)
               ]))
          [{:n 0, :ne 0, :nw 0} 0] moves))

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
  ;; -41
  ;; 0 = x + y + z
  ;; 0 = 41 + 449 - 369
  ;; minimize |x| + |y| + |z|

  (def m {:n 41, :nw -369, :ne 449})
  (mag m) ;; 859
  (mag (xf m 1)) ;; 860
  (mag (xf m -1)) ;; 858

  (find-min {:n -2, :ne 2, :nw 0})[{:n 0, :ne 0, :nw -2} 2]

  (find-min m)
  (solve) ;; [{:n 41, :nw -369, :ne 449} 1596]
  )
