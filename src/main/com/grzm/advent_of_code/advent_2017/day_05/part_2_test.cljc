(ns com.grzm.advent-of-code.advent-2017.day-05.part-2-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-05.part-2 :as part-2]))

(deftest jump
  (are [expected input] (= expected (apply part-2/jump input))
    [0 [1 3  0  1  -3]] [0 [0 3  0  1  -3]]))

(deftest solve
  (are [expected input] (= expected (part-2/solve input))
    10 [0 3  0  1  -3]))
