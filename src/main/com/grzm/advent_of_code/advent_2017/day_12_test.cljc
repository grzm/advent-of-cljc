(ns com.grzm.advent-of-code.advent-2017.day-12-test
  (:require
   [clojure.test :refer [deftest is]]
   [com.grzm.advent-of-code.advent-2017.day-12 :as day-12]))

(def sample-input "0 <-> 2
  1 <-> 1
  2 <-> 0, 3, 4
  3 <-> 2, 4
  4 <-> 2, 3, 6
  5 <-> 6
  6 <-> 4, 5")

(deftest part-1-test
  (is (= 6 (day-12/part-1 sample-input))))

(deftest part-2-test
  (is (= 2 (day-12/part-2 sample-input))))

