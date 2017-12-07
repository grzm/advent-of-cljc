(ns com.grzm.advent-of-code.advent-2017.day-07.part-2
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-07.core :as core]
   [com.grzm.advent-of-code.advent-2017.day-07.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-07.part-1 :as part-1])
  (:import (clojure.lang ExceptionInfo)))

(defn make-proc-map [procs]
  (reduce (fn [proc-map {:keys [proc weight] :as m}]
            (assoc proc-map proc m)) {} procs))

(defn find-balance [subprocs]
  (let [weights (group-by :subweight subprocs)

        ;; this is some of the deepest destructuring I've done
        [[_ [{:keys [weight subweight] :as singleton} & _]] [common-weight _]]
        (sort-by (fn [[_ v]] (count v)) weights)]
    (+ weight (- common-weight subweight))))

(defn subweights
  ([proc-map proc-name]
   (subweights (atom {}) proc-map proc-name))
  ([memo proc-map proc-name]
   (let [{:keys [proc subprocs weight]
          :as   p} (get proc-map proc-name)
         new-p     (if (seq subprocs)
                     (let [ss        (map #(subweights memo proc-map %) subprocs)
                           ssw       (map :subweight ss)
                           balanced? (apply = ssw)]
                       (when-not balanced?
                         (let [weights (group-by :subweight ss)]
                           (throw (ex-info "we're unbalanced"
                                           {:balance (find-balance ss)}))))
                       (assoc p :subweight (+ weight (apply + ssw)) :balanced? (apply = ssw)))
                     (assoc p :subweight weight :balanced? true))]
     (swap! memo assoc proc-name new-p)
     new-p)))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (let [procs (core/parse input)
         p-map (make-proc-map procs)
         root  (part-1/solve* procs)
         memo  (atom {})]
     (:balance (try
                 (subweights  p-map root)
                 (catch ExceptionInfo e
                   (ex-data e)))))))

(comment
  (def mini-input "pbga (66)
  xhth (57)
  ebii (61)
  havc (66)
  ktlj (57)
  fwft (72) -> ktlj, cntj, xhth
  qoyq (66)
  padx (45) -> pbga, havc, qoyq
  tknk (41) -> ugml, padx, fwft
  jptl (61)
  ugml (68) -> gyxo, ebii, jptl
  gyxo (61)
  cntj (57)")

  (def subprocs '({:balanced? true,
                   :proc      "ugml",
                   :subprocs  ["gyxo" "ebii" "jptl"],
                   :subweight 251,
                   :weight    68}
                  {:balanced? true,
                   :proc      "padx",
                   :subprocs  ["pbga" "havc" "qoyq"],
                   :subweight 243,
                   :weight    45}
                  {:balanced? true,
                   :proc      "fwft",
                   :subprocs  ["ktlj" "cntj" "xhth"],
                   :subweight 243,
                   :weight    72}))

  (find-balance subprocs)

  (solve mini-input)
  (solve)
  )
