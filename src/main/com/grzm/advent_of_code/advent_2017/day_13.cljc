(ns com.grzm.advent-of-code.advent-2017.day-13
  (:require
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-13.data :as data]
   [com.grzm.advent-of-code.advent-2017.util :as util]
   #?(:clj [criterium.core :refer [quick-bench]])))

(defn parse-line
  [line]
  (let [vals (str/split (str/trim line) #": ")]
    (mapv util/parse-int vals)))

(defn parse
  "Returns a sequence of [depth range] vectors"
  [input]
  (->> (str/split-lines input)
       (mapv parse-line)))

(defn range-cycle* [r]
  (concat (range r) (reverse (range 1 (dec r)))))

(defn damage-at-fn [[d r]]
  (let [len (count (range-cycle* r))
        damage (* d r)]
    (fn [t]
      (if (zero? (mod (+ t d) len)) damage 0))))

(defn damage-at-t [d-rs]
  (->> (map damage-at-fn d-rs)
       (apply juxt)))

(defn part-1
  ([]
   (part-1 data/input))
  ([input]
   (let [damage-at (->> (parse input)
                        damage-at-t)]
     (apply + (damage-at 0)))))

(defn pos-at-fn [[d r]]
  (let [rc  (vec (range-cycle* r))
        len (count rc)]
    (fn [t] (rc (mod (+ t d) len)))))

(defn pos-at-t [d-rs]
  (->> (map pos-at-fn d-rs)
       (apply juxt)))

(defn part-2
  ([]
   (part-2 data/input))
  ([input]
   (let [pos-at (pos-at-t (parse input))]
     (->> (range)
          (drop-while #(some zero? (pos-at %)))
          first))))

(comment

  (def sample-input "0: 3
  1: 2
  4: 4
  6: 4")

  (part-1 sample-input)
  (part-1)

  (part-2 sample-input)
  (part-2)

  )
