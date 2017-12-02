(ns com.grzm.advent-of-code.advent-2017.day-01.part-2-test
  (:require
   [clojure.test :refer [deftest are]]
   [com.grzm.advent-of-code.advent-2017.day-01.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-01.part-2 :as part-2]))

(deftest solve
  (are [ret s] (= ret (part-2/solve s))
    6 "1212"
    0 "1221"
    4 "123425"
    12 "123123"
    4 "12131415"
    1166 data/input))
