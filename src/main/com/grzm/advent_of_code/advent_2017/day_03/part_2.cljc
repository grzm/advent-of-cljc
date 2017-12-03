(ns com.grzm.advent-of-code.advent-2017.day-03.part-2
  (:require
   [com.grzm.advent-of-code.advent-2017.day-03 :as day-03]))

(defn surrounds
  "Calculate coords of cells surrounding the given cell"
  [[x y :as xy]]
  (->>
    (for [x'    [-1 0 1]
          y'    [-1 0 1]
          :when (not (and (zero? x') (zero? y')))]
      (vector x' y'))
    (map #(day-03/vector-add xy %))
    set))

(defn solve
  [input]
  (loop [[n & r :as paths] day-03/location-paths
         [l :as seen]      (list {:loc [0 0] :val 1})]
    (let [xy             (day-03/vector-add n (:loc l))
          val            (transduce (comp
                                      (filter #((surrounds xy) (:loc %)))
                                      (map :val)) + seen)]
      (if (> val input)
        val
        (recur r (conj seen {:loc xy :val val}))))))
