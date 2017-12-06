(ns com.grzm.advent-of-code.advent-2017.day-06.part-1-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-06.part-1 :as part-1]))

(deftest solve
  (are [expected input] (= expected (part-1/solve input))
    5 [0 2 7 0]))
