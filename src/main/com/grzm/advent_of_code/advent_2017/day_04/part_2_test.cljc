(ns com.grzm.advent-of-code.advent-2017.day-04.part-2-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-04.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-04.part-2 :as part-2]))

(deftest valid-passphrase?
  (are [expected input] (= expected (part-2/valid-passphrase? input))
    true  "abcde fghij"
    false "abcde xyz ecdab"
    true  "a ab abc abd abf abj"
    true  "iiii oiii ooii oooi oooo"
    false "oiii ioii iioi iiio"))

(deftest solve
  (is (= 223 (part-2/solve data/input))))
