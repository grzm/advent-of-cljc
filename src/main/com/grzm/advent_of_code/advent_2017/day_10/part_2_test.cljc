(ns com.grzm.advent-of-code.advent-2017.day-10.part-2-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-10.part-2 :as part-2]))

(deftest parse
  (is (= [49,44,50,44,51] (part-2/parse "1,2,3"))))

(deftest solve
  (are [expected input] (= expected (part-2/solve input))
    "a2582a3a0e66e6e86e3812dcb672a272" ""
    "33efeb34ea91902bb2f59c9920caa6cd" "AoC 2017"
    "3efbe78a8d82f29979031a4aa0b16a9d" "1,2,3"
    "63960835bcdc130f0b66d7ff4f6a5a8e" "1,2,4"))
