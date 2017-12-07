(ns com.grzm.advent-of-code.advent-2017.day-07.core
  (:require
   [clojure.string :as str]))

#?(:clj (defn parse-int [v]
          (Integer/parseInt v)))

#?(:cljs (defn parse-int [v]
           (js/parseInt v)))

(defn parse-line [line]
  (let [[proc w-str & r] (str/split line #" ")]
    {:proc     proc
     :weight   (parse-int (re-find #"\d+" w-str))
     :subprocs (when (seq r)
                 (mapv #(str/replace % #"," "") (rest r)))}))

(defn parse [input]
  (->> (str/split-lines input)
       (map str/trim)
       (remove #(= % ""))
       (map parse-line)))
