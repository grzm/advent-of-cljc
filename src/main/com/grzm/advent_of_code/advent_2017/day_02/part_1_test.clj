(ns com.grzm.advent-of-code.advent-2017.day-02.part-1-test
  (:require
   [clojure.test :refer [deftest are]]
   [com.grzm.advent-of-code.advent-2017.day-02.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-02.part-1 :refer [solve]]))

(deftest solve-test
  (are [expected input] (= expected (solve input))
    18    [[5 1 9 5]
           [7 5 3]
           [2 4 6 8]]
    53460 (data/parse data/input)))
