(ns com.grzm.advent-of-code.advent-2017.day-04.part-2
  (:require
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-04.data :as data]))

(defn any-anagrams?
  [words]
  (->> words
       (map sort)
       frequencies
       vals
       (filter #(< 1 %))
       seq
       boolean))

(defn valid-passphrase?
  [s]
  (not (any-anagrams? (str/split s #" +"))))

(defn solve
  [input]
  (->> (str/split-lines input)
       (filter valid-passphrase?)
       count))
