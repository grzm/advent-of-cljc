(ns com.grzm.advent-of-code.advent-2017.day-01.part-2-test
  (:require
   [clojure.test :refer [deftest are]]
   [com.grzm.advent-of-code.advent-2017.day-01.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-01.part-2 :refer [solve]]))

(deftest solve-test
  (are [ret s] (= ret (solve s))
    6 "1212"
    0 "1221"
    4 "123425"
    12 "123123"
    4 "12131415"
    1166 data/input))
