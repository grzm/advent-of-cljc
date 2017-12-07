(ns com.grzm.advent-of-code.advent-2017.day-07.core-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-07.core :as core]))

#_(
   deftest redistribute
  (are [expected input] (= expected (core/redistribute input))
    [2 4 1 2] [0 2 7 0]
    [3 1 2 3] [2 4 1 2]
    [0 2 3 4] [3 1 2 3]))
