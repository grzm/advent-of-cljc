(ns com.grzm.advent-of-code.advent-2017.day-06.part-1
  (:require
   [clojure.string :as str]
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-06.data :as data]))

(defn max-index
  [locs]
  (->> (map vector (range) locs)
       reverse
       (apply max-key second)))

(defn init [locs]
  (let [[i m] (max-index locs)]
    [i (->> (map vector (drop (inc i) (cycle (range (count locs)))) (repeat m 1))
            frequencies
            (map #(vector (ffirst %) (second %))))]))

(defn redistribute
  [locs]
  (let [[i ops] (init locs)]
    (reduce (fn [ret [i op]]
              (update-in ret [i] #(+ op %)))
            (assoc locs i 0)
            ops)))

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
