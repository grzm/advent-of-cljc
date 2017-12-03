(ns com.grzm.advent-of-code.advent-2017.day-02.part-2-test
  (:require
   [clojure.test :refer [deftest are]]
   [com.grzm.advent-of-code.advent-2017.day-02.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-02.part-2 :as part-2]))

(deftest solve
  (are [expected input] (= expected (part-2/solve input))
    9   [[5 9 2 8]
         [9 4 7 3]
         [3 8 6 5]]
    282 (data/parse data/input)))
