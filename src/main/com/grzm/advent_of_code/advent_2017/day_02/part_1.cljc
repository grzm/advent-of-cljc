(ns com.grzm.advent-of-code.advent-2017.day-02.part-1
  (:require
   [clojure.string :as str]))

(defn solve
  [rows]
  (transduce (map #(- (apply max %) (apply min %))) + rows))
