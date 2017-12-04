(ns com.grzm.advent-of-code.advent-2017.day-04.part-1-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-04.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-04.part-1 :as part-1]))

(deftest valid-passphrase?
  (are [expected input] (= expected (part-1/valid-passphrase? input))
    true  "aa bb cc dd ee"
    false "aa bb cc dd aa"
    true  "aa bb cc dd aaa"))

(deftest solve
  (is (= 451 (part-1/solve data/input))))
