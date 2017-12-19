(ns com.grzm.advent-of-code.advent-2017.day-19
  (:require
   [clojure.string :as str]
   [clojure.test :refer [deftest is are]]
   [com.grzm.advent-of-code.advent-2017.day-19.data :as data]))

;; --- Day 19: A Series of Tubes ---

;; Somehow, a network packet got lost and ended up here. It's trying to follow a
;; routing diagram (your puzzle input), but it's confused about where to go.

;; Its starting point is just off the top of the diagram. Lines (drawn with |, -,
;; and +) show the path it needs to take, starting by going down onto the only line
;; connected to the top of the diagram. It needs to follow this path until it
;; reaches the end (located somewhere within the diagram) and stop there.

;; Sometimes, the lines cross over each other; in these cases, it needs to continue
;; going the same direction, and only turn left or right when there's no other
;; option. In addition, someone has left letters on the line; these also don't
;; change its direction, but it can use them to keep track of where it's been. For
;; example:

;;      |
;;      |  +--+
;;      A  |  C
;;  F---|----E|--+
;;      |  |  |  D
;;      +B-+  +--+

;; Given this diagram, the packet needs to take the following path:

;; - Starting at the only line touching the top of the diagram, it must go down,
;;   pass through A, and continue onward to the first +.
;; - Travel right, up, and right, passing through B in the process.
;; - Continue down (collecting C), right, and up (collecting D).
;; - Finally, go all the way left through E and stopping at F.
;; - Following the path to the end, the letters it sees on its path are ABCDEF.

;; The little packet looks up at you, hoping you can help it find the way. What
;; letters will it see (in the order it would see them) if it follows the path?
;; (The routing diagram is very wide; make sure you view it without line wrapping.)

(def sample-input
  "     |
     |  +--+
     A  |  C
 F---|----E|--+
     |  |  |  D
     +B-+  +--+
  ")

(defn parse [input]
  (->> (str/split-lines input)
       (map #(str/split % #""))
       vec))

(def sample-diag (parse sample-input))

(def dir-fn {:n (fn [[x y]] [(dec x) y])
             :s (fn [[x y]] [(inc x) y])
             :e (fn [[x y]] [x (inc y)])
             :w (fn [[x y]] [x (dec y)])})


(defn choose [[diag [x y :as pos] dir seen]]
  (let [new-dir (ffirst (remove (fn [[s f]] (str/blank? (get-in diag (f pos))))
                                (dissoc dir-fn (case dir :n :s, :s :n, :e :w, :w :e))))]
    [diag ((new-dir dir-fn) pos) new-dir seen]))

;; could tell direction from where we've been

(defn nav [[diag [x y :as pos] dir seen]]
  (let [p (get-in diag pos)]
    (case p
      "|" [diag ((dir dir-fn) pos) dir seen]
      "-" [diag ((dir dir-fn) pos) dir seen]
      "+" (choose [diag pos dir seen])
      [diag ((dir dir-fn) pos) dir (conj seen p)])))

(defn starting-pos [diag]
  (->> (first diag)
       (map-indexed (fn [i e] [i e]))
       (filter #(= "|" (second %)))
       ffirst
       (vector 0)))

(comment
  (parse sample-input)
  (starting-pos sample-diag)
  (nav [sample-diag [0 5] :s []])
  (nav [sample-diag [5 11] :s ["A" "B" "C"]])
  )

(defn part-1
  ([]
   (part-1 data/input))
  ([input]
   (let [diag (parse input)]
     (loop [pos (starting-pos diag)
            dir :s
            seen []]
       (if (str/blank? (get-in diag pos))
         (apply str seen)
         (let [[_ pos' dir' seen'] (nav [diag pos dir seen])]
           (recur pos' dir' seen')))))))

(comment
  (part-1 sample-input)
  (part-1) ;; "DWNBGECOMY"
  )


;; The packet is curious how many steps it needs to go.

;; For example, using the same routing diagram from the example above...

;;     |
;;     |  +--+
;;     A  |  C
;; F---|--|-E---+
;;     |  |  |  D
;;     +B-+  +--+

;; Note, this second diagram is *not* the same as that above, and it's wrong

;; ...the packet would go:

;; - 6 steps down (including the first line at the top of the diagram).
;; - 3 steps right.
;; - 4 steps up.
;; - 3 steps right.
;; - 4 steps down.
;; - 3 steps right.
;; - 2 steps up.
;; - 13 steps left (including the F it stops on).

;; This would result in a total of 38 steps.

;; How many steps does the packet need to go?

(defn part-2
  ([]
   (part-2 data/input))
  ([input]
   (let [diag (parse input)]
     (loop [n 0
            pos  (starting-pos diag)
            dir  :s
            seen []]
       (if (str/blank? (get-in diag pos))
         n
         (let [[_ pos' dir' seen'] (nav [diag pos dir seen])]
           (recur (inc n) pos' dir' seen')))))))

(comment

  (part-2 sample-input)
  (part-2) ;;17228
  )
