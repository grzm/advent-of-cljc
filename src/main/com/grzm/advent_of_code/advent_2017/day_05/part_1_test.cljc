(ns com.grzm.advent-of-code.advent-2017.day-05.part-1-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-05.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-05.part-1 :as part-1]))

(deftest jump
  (are [expected input] (= expected (apply part-1/jump input))
    [0 [1 3  0  1  -3]] [0 [0 3  0  1  -3]]))
