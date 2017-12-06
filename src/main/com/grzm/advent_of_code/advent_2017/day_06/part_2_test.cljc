(ns com.grzm.advent-of-code.advent-2017.day-06.part-2-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-06.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-06.part-2 :as part-2]))

(deftest solve
  (are [expected input] (= expected (part-2/solve input))
    4 [0 2 7 0]))
