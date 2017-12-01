(ns com.grzm.advent-of-code.advent-2017.day-01.part-2)

(defn solve
  [xs]
  (let [r (/ (count xs) 2)]
    (->> (map vector xs (concat (nthrest xs (/ (count xs) 2)) xs))
         (reduce (fn [ret [a b]]
                   (+ ret (if (= a b) (- (int a) 48) 0)))
                 0))))
