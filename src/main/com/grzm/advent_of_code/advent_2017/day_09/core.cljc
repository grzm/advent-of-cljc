(ns com.grzm.advent-of-code.advent-2017.day-09.core)

;; assuming data is well-formed
;; states :init :group :garbage :ignore :done
;; also, keep track of depth (current),
;; and count (increment every time we increase depth)

(defn throw-invalid-transition
  [state c]
  (throw (ex-info "invalid state transition"
                  {:state state, :c c})))

(defn p*-dispatch [state c]
  (:state state))

(defmulti p* p*-dispatch)

(defmethod p* :default
  [state c]
  (throw (ex-info "unknown state"
                  {:state state, :c c})))

(defmethod p* :init
  [state c]
  (if (= c \{)
    (assoc state :state :group, :depth 1, :count 1, :garbage 0, :c c)
    (throw-invalid-transition state c)))

(defmethod p* :group
  [state c]
  (condp = c
    \{ (let [depth (:depth state)]
         (-> state
             (update :depth inc)
             (update :count #(+ (inc depth) %))
             (assoc :c c)))
    \} (if (= 1 (:depth state))
         (assoc state :state :done :depth 0 :c c)
         (-> (update state :depth dec)
             (assoc :c c)))
    \< (assoc state :state :garbage, :c c)
    \, (assoc state :c c)
    :else (throw-invalid-transition state c)))

(defmethod p* :garbage
  [state c]
  (condp = c
    \! (assoc state :state :ignore, :c c)
    \> (assoc state :state :group, :c c)
    (-> state
        (update :garbage inc)
        (assoc :c c))))

(defmethod p* :ignore
  [state c]
  (assoc state :state :garbage, :c c))
