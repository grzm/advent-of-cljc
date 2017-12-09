(ns com.grzm.advent-of-code.advent-2017.day-09.part-1-test
  (:require
   [clojure.test :refer [deftest are is]]
   [com.grzm.advent-of-code.advent-2017.day-09.core :as core]
   [com.grzm.advent-of-code.advent-2017.day-09.part-1 :as part-1]))

(deftest filter-garbage
  (let [init-state {:state :group, :depth 1, :count 1, :garbage 0}]
    (are [input expected] (= (assoc expected :c \>)
                             (part-1/solve* init-state input))
      "<>" init-state
      "<random characters>" (assoc init-state :garbage 17)
      "<<<<>" (assoc init-state :garbage 3)
      "<{!>}>" (assoc init-state :garbage 2)
      "<!!>" init-state
      "<!!!>>" init-state
      "<{o\"i!a,<{i<a>" (assoc init-state :garbage 10))))

(deftest solve
  (are [expected input] (= expected (part-1/solve input))
    1 "{}"
    6 "{{{}}}"
    5 "{{},{}}"
    16 "{{{},{},{{}}}}"
    1 "{<{},{},{{}}>}"
    1 "{<a>,<a>,<a>,<a>}"
    9 "{{<ab>},{<ab>},{<ab>},{<ab>}}"
    9 "{{<!!>},{<!!>},{<!!>},{<!!>}}"
    3 "{{<a!>},{<a!>},{<a!>},{<ab>}}"))

(comment
  (reductions core/p-case {:state :init} (seq "{{},{}}"))
  ({:state :init}
   {:state :group, :depth 1, :count 1, :c \{}
   {:state :group, :depth 2, :count 3, :c \{}
   {:state :group, :depth 1, :count 3, :c \}}
   {:state :group, :depth 1, :count 3, :c \}}
   {:state :group, :depth 2, :count 5, :c \{}
   {:state :group, :depth 1, :count 5, :c \}}
   {:state :done, :depth 0, :count 5, :c \}})



  )
