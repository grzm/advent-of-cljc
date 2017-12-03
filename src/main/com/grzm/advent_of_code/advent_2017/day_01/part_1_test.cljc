(ns com.grzm.advent-of-code.advent-2017.day-01.part-1-test
  (:require
   [clojure.test :refer [deftest are]]
   [com.grzm.advent-of-code.advent-2017.day-01.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-01.part-1 :as part-1]))

(deftest solve
  (are [ret s] (= ret (part-1/solve (data/parse s)))
    3    "1122"
    4    "1111"
    0    "1234"
    9    "91212129"
    1175 data/input))
