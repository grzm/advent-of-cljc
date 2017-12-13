(ns com.grzm.advent-of-code.advent-2017.day-13-test
  (:require
   [clojure.test :refer [deftest is]]
   [com.grzm.advent-of-code.advent-2017.day-13 :as day-13]))

(def sample-input "0: 3
  1: 2
  4: 4
  6: 4")

(deftest part-1-test
  (is (= 24 (day-13/part-1 sample-input))))

(deftest part-2-test
  (is (= 10 (day-13/part-2 sample-input))))

(deftest part-2-test
  (is (= 10 (day-13/part-2* sample-input))))
