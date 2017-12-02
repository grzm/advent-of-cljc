(ns com.grzm.advent-of-code.advent-2017.day-02.part-2)

(defn evenly-divisible-quotient
  [xs]
  (loop [[d & ns] (sort xs)]
    (if-let [n (first (filter #(zero? (rem % d)) ns))]
      (/ n d)
      (recur ns))))

(defn solve
  [rows]
  (reduce (fn [ret row]
            (+ ret (evenly-divisible-quotient row))) 0 rows))
