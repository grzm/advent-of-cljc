(ns com.grzm.advent-of-code.advent-2017.day-03.part-1
  (:require
   [com.grzm.advent-of-code.advent-2017.day-03 :as day-03]))

(defn solve
  [location]
  (->> day-03/location-paths
       (transduce (take (dec location)) day-03/vector-add)
       (transduce (map #?(:clj #(Math/abs %)
                          :cljs #(.abs js/Math %))) +)))

(comment
  "
17  16  15  14  13
18   5   4   3  12
19   6   1   2  11
20   7   8   9  10
21  22  23---> ...

  1        [0 0]
  2  x + 1 [1 0]
  3  y + 1 [1 1]
  4  x - 1 [0 1]
  5  x - 1 [-1 1]
  6  y - 1 [-1 0]
  7  y - 1 [-1 -1]
  8  x + 1 [0 -1]
  9  x + 1 [1 -1]
 10  x + 1 [2 -1]
 11  y + 1 [2 0]
 12  y + 1 [2 1]
 13  y + 1 [2 2]
 14  x - 1 [1 2]
 15  x - 1
 16  x - 1
 17  x - 1
 18  y - 1
 19  y - 1
 20  y - 1
 21  y - 1
 22  x + 1
 23  x + 1


 [0 0]  (range 0)

 [1 0]  (range 1)
 [0 1]

 [-1 0] (range 2)
 [-1 0]
 [0 -1]
 [0 -1]

 [1 0]  (range 3)
 [1 0]
 [1 0]
 [0 1]
 [0 1]
 [0 1]

 [-1 0]
 [-1 0]
 [-1 0]
 [-1 0]
 [0 -1]
 [0 -1]
 [0 -1]
 [0 -1]
"
  )
