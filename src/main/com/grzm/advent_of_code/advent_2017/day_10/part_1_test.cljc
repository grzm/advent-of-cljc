(ns com.grzm.advent-of-code.advent-2017.day-10.part-1-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-10.part-1 :as part-1]))

(deftest hashish
  (are [input expected] (= expected (apply part-1/hashish input))
    [{:st [0 1 2 3 4] :c 5 :i 0 :skip 0} 3]
    {:st [2 1 0 3 4] :c 5 :i 3 :skip 1}

    [{:st [2 1 0 3 4] :c 5 :i 3 :skip 1} 4]
    {:st [4 3 0 1 2] :c 5 :i 3 :skip 2}

    [{:st [4 3 0 1 2] :c 5 :i 3 :skip 2} 1]
    {:st [4 3 0 1 2] :c 5 :i 1 :skip 3}

    [{:st [4 3 0 1 2] :c 5 :i 1 :skip 3} 5]
    {:st [3 4 2 1 0] :c 5 :i 4 :skip 4}))

(deftest solve*
  (is (= [3 4 2 1 0] (part-1/solve* 5 [3 4 1 5]))))

(deftest solve
  (is (= 12 (part-1/solve 5 "3,4,1,5"))))
