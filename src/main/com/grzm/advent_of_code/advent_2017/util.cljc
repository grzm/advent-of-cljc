(ns com.grzm.advent-of-code.advent-2017.util)

#?(:clj (defn parse-int [v]
          (Integer/parseInt v)))

#?(:cljs (defn parse-int [v]
           (js/parseInt v)))
