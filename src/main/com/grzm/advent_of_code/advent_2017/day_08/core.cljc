(ns com.grzm.advent-of-code.advent-2017.day-08.core
  (:require
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.util :as util]))

(def ops {"!=" not=
          ">" >
          "<" <
          ">=" >=
          "<=" <=
          "==" =
          "dec" -
          "inc" +})

(defn op
  [registers
   [r rop-k v-str _if pred-r cmp-k cmp-str :as instr]]
  (let [rop (ops rop-k)
        v (util/parse-int v-str)
        cmp (ops cmp-k)
        pred-val (get registers pred-r 0)
        cmp-val (util/parse-int cmp-str)]
    (if (cmp pred-val cmp-val)
      (update registers r (fnil #(rop % v) 0))
      registers)))

(defn parse [s]
  (->> (str/split-lines s)
       (transduce (comp
                   (remove str/blank?)
                   (map str/trim)
                   (map #(str/split % #" "))) conj)))

(comment
  (def sample
    "b inc 5 if a > 1
  a inc 1 if b < 5
  c dec -10 if a >= 1
  c inc -20 if c == 10")

  (parse sample)
  )
