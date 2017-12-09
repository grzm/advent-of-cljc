(ns com.grzm.advent-of-code.advent-2017.day-09.core-test
  (:require
   [clojure.test :refer [deftest are]]
   [com.grzm.advent-of-code.advent-2017.day-09.core :as core]))

(deftest p*-test
  (are [input expected] (= expected (apply core/p-case input))
    [{:state :init} \{]
    {:state :group, :depth 1, :count 1, :garbage 0, :c \{}

    [{:state :group, :depth 1, :count 1} \{]
    {:state :group, :depth 2, :count 3, :c \{}

    [{:state :group} \<]
    {:state :garbage, :c \<}

    [{:state :garbage, :garbage 0} \}]
    {:state :garbage, :garbage 1 :c \}}

    [{:state :garbage} \!]
    {:state :ignore, :c \!}

    [{:state :garbage} \>]
    {:state :group, :c \>}

    [{:state :ignore} \!]
    {:state :garbage, :c \!}))


(comment
  )
