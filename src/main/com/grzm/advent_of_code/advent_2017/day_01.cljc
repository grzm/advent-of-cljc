(ns com.grzm.advent-of-code.advent-2017.day-01)

(def x-form
  (comp (filter (partial apply =))
        (map first)))
