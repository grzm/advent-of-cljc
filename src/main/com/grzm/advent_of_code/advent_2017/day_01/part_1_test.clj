(ns com.grzm.advent-of-code.advent-2017.day-01.part-1-test
  (:require
   [clojure.test :refer [deftest are]]
   [com.grzm.advent-of-code.advent-2017.day-01.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-01.part-1 :refer [solve]]))

(deftest solve-test
  (are [ret s] (= ret (solve s))
    3 "1122"
    4 "1111"
    0 "1234"
    9 "91212129"
    1175  data/input))
