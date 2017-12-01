(ns com.grzm.advent-of-code.advent-2017.day-01.part-1)

(defn solve
  [xs]
  (->> (concat (partition 2 1 xs)
               (list (list (last xs) (first xs))))
       (reduce (fn [ret [a b]]
                 (+ ret (if (= a b)
                          (- (int a) 48) 0))) 0)))
