(ns com.grzm.advent-of-code.advent-2017.day-08.part-1-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-08.part-1 :as part-1]))

(deftest solve
  (are [expected input] (= expected (part-1/solve input))
    1 "b inc 5 if a > 1
  a inc 1 if b < 5
  c dec -10 if a >= 1
  c inc -20 if c == 10"))
