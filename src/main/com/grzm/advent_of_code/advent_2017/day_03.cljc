(ns com.grzm.advent-of-code.advent-2017.day-03)

(defn vector-add
  ([] [0 0])
  ([ret] ret)
  ([[x y] [x' y']] [(+ x x') (+ y y')]))

(def location-paths
  (->>
    (map vector
         (cycle [1 0 -1 0])
         (cycle [0 1 0 -1]))
    (partition 2)
    (map vector (iterate inc 1))
    (mapcat (fn [[r [x y]]]
              (concat (repeat r x) (repeat r y))))))
