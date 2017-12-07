(ns com.grzm.advent-of-code.advent-2017.day-07.part-1
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-07.core :as core]
   [com.grzm.advent-of-code.advent-2017.day-07.data :as data]))

;; has children, and doesn't appear in a child list
(defn solve*
  [procs]
  (let [[procs subprocs]
        (reduce (fn [[p s]
                     {:keys [proc subprocs]}]
                  [(conj p proc) (concat s subprocs)])
                [#{} (list)] procs)]
    (first (set/difference procs (set subprocs)))))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (solve* (core/parse input))))

(comment
  (solve)

  (def mini-input "pbga (66)
  xhth (57)
  ebii (61)
  havc (66)
  ktlj (57)
  fwft (72) -> ktlj, cntj, xhth
  qoyq (66)
  padx (45) -> pbga, havc, qoyq
  tknk (41) -> ugml, padx, fwft
  jptl (61)
  ugml (68) -> gyxo, ebii, jptl
  gyxo (61)
  cntj (57)")
  (core/parse mini-input)
  )
