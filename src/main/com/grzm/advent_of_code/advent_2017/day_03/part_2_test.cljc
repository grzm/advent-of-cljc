(ns com.grzm.advent-of-code.advent-2017.day-03.part-2-test
  (:require
   [clojure.test :refer [deftest are]]
   [com.grzm.advent-of-code.advent-2017.day-03.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-03.part-2 :as part-2]))

(deftest solve
  (are [expected input] (= expected (part-2/solve input))
    349975 data/input))
