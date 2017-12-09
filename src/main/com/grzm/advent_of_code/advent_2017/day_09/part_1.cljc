(ns com.grzm.advent-of-code.advent-2017.day-09.part-1
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-09.core :as core]
   [com.grzm.advent-of-code.advent-2017.day-09.data :as data]))

(defn solve*
  [init-state input]
  (reduce core/p* init-state (seq input)))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (solve {:state :init} input))
  ([init-state input]
   (:count (solve* init-state input))))

(comment
  (solve)
  )
