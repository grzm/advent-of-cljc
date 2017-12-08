(ns com.grzm.advent-of-code.advent-2017.day-08.part-2-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-08.part-2 :as part-2]))

(deftest solve
  (are [expected input] (= expected (part-2/solve input))
    10 "b inc 5 if a > 1
  a inc 1 if b < 5
  c dec -10 if a >= 1
  c inc -20 if c == 10"))
