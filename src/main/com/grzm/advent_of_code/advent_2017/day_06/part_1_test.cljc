(ns com.grzm.advent-of-code.advent-2017.day-06.part-1-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-06.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-06.part-1 :as part-1]))

(deftest redistribute
  (are [expected input] (= expected (part-1/redistribute input))
    [2 4 1 2] [0 2 7 0]
    [3 1 2 3] [2 4 1 2]
    [0 2 3 4] [3 1 2 3]))

(deftest solve
  (are [expected input] (= expected (part-1/solve input))
    5 [0 2 7 0]))
