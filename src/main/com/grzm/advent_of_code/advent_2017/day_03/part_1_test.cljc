(ns com.grzm.advent-of-code.advent-2017.day-03.part-1-test
  (:require
   [clojure.test :refer [deftest are]]
   [com.grzm.advent-of-code.advent-2017.day-03.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-03.part-1 :as part-1]))

(deftest solve
  (are [expected input] (= expected (part-1/solve input))
    480 data/input))
