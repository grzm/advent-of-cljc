(ns com.grzm.advent-of-code.advent-2017.day-06.core)

(defn max-index
  [locs]
  (->> (map vector (range) locs)
       (reduce (fn [[_ max-val :as m]
                    [_ cur-val :as c]]
                 (if (> cur-val max-val)
                   c m)))))

(defn init [locs]
  (let [[i m] (max-index locs)]
    [i (->> (map vector (drop (inc i) (cycle (range (count locs)))) (repeat m 1))
            frequencies
            (map #(vector (ffirst %) (second %))))]))

(defn redistribute
  [locs]
  (let [[i ops] (init locs)]
    (reduce (fn [ret [i op]]
              (update-in ret [i] #(+ op %)))
            (assoc locs i 0)
            ops)))

