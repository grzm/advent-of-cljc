(ns com.grzm.advent-of-code.advent-2017.day-15
  (:require
   [clojure.string :as str]
   [clojure.test :refer [deftest is]]
   [com.grzm.advent-of-code.advent-2017.util :as util]
   #?(:clj [criterium.core :refer [quick-bench]])))

(def input "Generator A starts with 699
  Generator B starts with 124")

(defn parse-line [line]
  (->> (str/split line #" ")
       last
       util/parse-int))

(defn parse [input]
  (->> (str/split-lines input)
       (map parse-line)))

(defn crunch-fn [^long factor ^long divisor]
  (fn [^long prev]
    (rem (* factor prev) divisor)))

(def divisor 2147483647)
(def bit-mask 65535)

(defn part-1
  ([]
   (apply part-1 40000000 (map vector [16807 48271] (parse input))))
  ([rounds [factor-a seed-a] [factor-b seed-b]]
   (let [gen-a (crunch-fn factor-a divisor)
         gen-b (crunch-fn factor-b divisor)]
     (->> (map vector (iterate gen-a seed-a) (iterate gen-b seed-b))
          (transduce (comp
                       (drop 1)
                       (map #(= ^long (bit-and ^long bit-mask ^long (first %))
                                ^long (bit-and ^long bit-mask ^long (second %))))
                       (take rounds)
                       (filter identity))
                     conj)
          count))))

(deftest part-1-test
  (is (= 1 (part-1 5 [16807 65] [48271 8921]))))

(defn part-2
  ([]
   (apply part-2 5000000 (map vector [16807 48271] (parse input))))
  ([rounds [factor-a seed-a] [factor-b seed-b]]
   (let [gen-a (crunch-fn factor-a divisor)
         gen-b (crunch-fn factor-b divisor)]
     (->> (map vector
               (filter #(zero? ^long (mod ^long % 4)) (iterate gen-a seed-a))
               (filter #(zero? ^long (mod ^long % 8)) (iterate gen-b seed-b)))
          (transduce (comp
                       (map #(= ^long (bit-and 65535 ^long (first %)) ^long (bit-and 65535 ^long (second %))))
                       (take rounds)
                       (filter identity))
                     conj)
          count))))

(deftest part-2-test
  (is (= 1 (part-2 1056 [16807 65] [48271 8921]))))

(comment

  (part-1 5 [16807 65] [48271 8921])
  (map vector [16807 48271] (parse input));;([16807 699] [48271 124])

  (part-1 699 124 40000000);; 600
  (part-1)

  (part-2 65 8921 1056)
  (part-2 699 124 5000000) ;; 313

  (part-1 sample-input)
  (quick-bench (part-1))

  (part-2 sample-input)
  (part-2)
  (quick-bench (part-2))

  )
