(ns com.grzm.advent-of-code.advent-2017.day-04.part-1
  (:require
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-04.data :as data]))

(defn valid-passphrase?
  [phrase]
  (let [words (str/split phrase #" +")]
    (= (count words) (count (distinct words)))))

(defn solve
  [input]
  (->> (str/split-lines input)
       (filter valid-passphrase?)
       count))
