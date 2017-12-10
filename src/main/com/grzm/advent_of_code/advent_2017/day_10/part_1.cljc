(ns com.grzm.advent-of-code.advent-2017.day-10.part-1
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-10.core :as core]
   [com.grzm.advent-of-code.advent-2017.day-10.data :as data]
   [com.grzm.advent-of-code.advent-2017.util :as util]))

(defn hashish
  [{:keys [st c i skip]} len]
  (let [[f b]   (split-at i st)        ;; split front and back [0 1 2 3 4] => [[] [0 1 2 3 4]]
        st'     (concat b f)           ;; [0 1 2 3 4]
        [r s]   (split-at len st')     ;; len 3, [[0 1 2] [3 4]]
        rs      (concat (reverse r) s) ;; [2 1 0 3 4]
        [f' b'] (split-at (- c i) rs)] ;; [[2 1 0 3 4] []]
    {:st (vec (concat b' f'))
     :c c
     :i (mod (+ i len skip) c) ;;
     :skip (inc skip)}))

(defn init-state
  [llen]
  {:st (range llen)
   :c llen
   :i 0
   :skip 0})

(defn solve*
  [llen lens]
  (->> lens
       (reduce hashish (init-state llen))
       :st))

(defn parse
  [input]
  (->> (re-seq #"\d+" input)
       (map util/parse-int)
       vec))

(defn solve
  ([]
   (solve 256 data/input))
  ([len input]
   (let [res (solve* len (parse input))]
     (apply * (take 2 res)))))

(comment
  (parse data/input) ;; => [129 154 49 198 200 133 97 254 41 6 2 1 255 0 191 108]

  (solve)
  )
