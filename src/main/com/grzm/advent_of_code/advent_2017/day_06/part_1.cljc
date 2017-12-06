(ns com.grzm.advent-of-code.advent-2017.day-06.part-1
  (:require
   [clojure.string :as str]
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-06.data :as data]))

(defn max-index [locs]
  (let [m (apply max locs)
        i (->> (map vector (range) locs)
               (drop-while #(< (second %) m))
               ffirst)]
    [m i]))

(defn init [locs]
  (let [[m i] (max-index locs)]
    [i (->> (map vector (drop (inc i) (cycle (range (count locs)))) (repeat m 1))
            frequencies
            (map #(vector (ffirst %) (second %))))]))

(defn redistribute
  [locs]
  (let [[i ops] (init locs)]
    (loop [ops   ops
           locs' (assoc locs i 0)]
      (if (seq ops)
        (let [[op & ops'] ops]
          (recur ops'
                 (update-in locs' [(first op)] #(+ (second op) %))))
        locs'))))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (loop [steps 0
          seen  #{}
          locs  input]
     (if (seen locs)
       steps
       (recur (inc steps)
              (conj seen locs)
              (redistribute locs))))))

(comment
  (solve)
  )
