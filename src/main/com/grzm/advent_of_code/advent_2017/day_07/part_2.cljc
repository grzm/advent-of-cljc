(ns com.grzm.advent-of-code.advent-2017.day-07.part-2
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [com.grzm.advent-of-code.advent-2017.day-07.core :as core]
   [com.grzm.advent-of-code.advent-2017.day-07.data :as data]
   [com.grzm.advent-of-code.advent-2017.day-07.part-1 :as part-1])
  #?(:clj (:import
           (clojure.lang ExceptionInfo))))

(defn make-proc-map [procs]
  (reduce (fn [proc-map {:keys [proc weight] :as m}]
            (assoc proc-map proc m)) {} procs))

(defn find-balance [subprocs]
  (let [;; this is some of the deepest destructuring I've done
        [[_ [{:keys [weight subweight] :as singleton} & _]] [common-weight _]]
        (sort-by #(count (second %)) (group-by :subweight subprocs))]
    (+ weight (- common-weight subweight))))

(defn subweights
  [proc-map proc-name]
  (let [{:keys [proc subprocs weight] :as p} (get proc-map proc-name)
        subweight (if (seq subprocs)
                    (let [ss  (map #(subweights proc-map %) subprocs)
                          ssw (map :subweight ss)]
                      (when-not (apply = ssw)
                        (let [weights (group-by :subweight ss)]
                          (throw (ex-info "we're unbalanced"
                                          {:balance (find-balance ss)}))))
                      (+ weight (apply + ssw)))
                    weight)]
    (assoc p :subweight subweight)))

(defn solve
  ([]
   (solve data/input))
  ([input]
   (let [procs (core/parse input)
         proc-map (make-proc-map procs)
         root  (part-1/solve* procs)]
     (:balance (try
                 (subweights proc-map root)
                 #?(:clj (catch ExceptionInfo e
                           (ex-data e))
                    :cljs (catch js/Error e
                            (ex-data e))))))))

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
