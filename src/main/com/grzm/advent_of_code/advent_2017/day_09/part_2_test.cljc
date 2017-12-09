(ns com.grzm.advent-of-code.advent-2017.day-09.part-2-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-09.part-2 :as part-2]))

(deftest solve
  (are [expected input] (= expected (part-2/solve input))
    nil nil))
