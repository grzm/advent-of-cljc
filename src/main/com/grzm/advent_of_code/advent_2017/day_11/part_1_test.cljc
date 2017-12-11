(ns com.grzm.advent-of-code.advent-2017.day-11.part-1-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-11.part-1 :as part-1]))

(deftest solve*
  (are [expected input] (= expected (first
                                     (part-1/solve* (part-1/parse input))))
    {:n 0 :ne 3 :nw 0} "ne,ne,ne"
    {:n 0 :ne 0 :nw 0} "ne,ne,sw,sw"
    {:n 0 :ne 0 :nw -2} "ne,ne,s,s"
    {:n -2 :ne -1, :nw 0} "se,sw,se,sw,sw"))

(deftest solve
  (are [expected input] (= expected (part-1/solve input))
    3 "ne,ne,ne"
    0 "ne,ne,sw,sw"
    2 "ne,ne,s,s"
    3 "se,sw,se,sw,sw"))
