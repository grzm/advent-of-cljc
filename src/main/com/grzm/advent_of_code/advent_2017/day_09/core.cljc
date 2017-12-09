(ns com.grzm.advent-of-code.advent-2017.day-09.core)

;; assuming data is well-formed
;; states :init :group :garbage :ignore :done
;; also, keep track of depth (current),
;; and count (increment every time we increase depth)

(defn throw-invalid-transition
  [state c]
  (throw (ex-info "invalid state transition"
                  {:state state, :c c})))

;; using condp
(defmulti p-condp (fn [state c] (:state state)))

(defmethod p-condp :default
  [state c]
  (throw (ex-info "unknown state"
                  {:state state, :c c})))

(defmethod p-condp :init
  [state c]
  (if (= c \{)
    (assoc state :state :group, :depth 1, :count 1, :garbage 0, :c c)
    (throw-invalid-transition state c)))

(defmethod p-condp :group
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
    ;; :else
    (throw-invalid-transition state c)))

(defmethod p-condp :garbage
  [state c]
  (condp = c
    \! (assoc state :state :ignore, :c c)
    \> (assoc state :state :group, :c c)
    ;; :else
    (-> state
        (update :garbage inc)
        (assoc :c c))))

(defmethod p-condp :ignore
  [state c]
  (assoc state :state :garbage, :c c))


;; using case, as we have constant comparisons
(defmulti p-case (fn [state c] (:state state)))

(defmethod p-case :default
  [state c]
  (throw (ex-info "unknown state"
                  {:state state, :c c})))

(defmethod p-case :init
  [state c]
  (case c
    \{ (assoc state :state :group, :depth 1, :count 1, :garbage 0, :c c)
    (throw-invalid-transition state c)))

(defmethod p-case :group
  [state c]
  (case c
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
    ;; :else
    (throw-invalid-transition state c)))

(defmethod p-case :garbage
  [state c]
  (case c
    \! (assoc state :state :ignore, :c c)
    \> (assoc state :state :group, :c c)
    ;; :else
    (-> state
        (update :garbage inc)
        (assoc :c c))))

(defmethod p-case :ignore
  [state c]
  (assoc state :state :garbage, :c c))


;; one big switch statement

(defn p-all-case
  [{:keys [state depth] :as s} c]
  (case state
    :init (case c
           \{ (assoc state :state :group, :depth 1, :count 1, :garbage 0, :c c)
           (throw-invalid-transition state c))

    :group (case c
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
             ;; :else
             (throw-invalid-transition state c))

    :garbage (case c
               \! (assoc state :state :ignore, :c c)
               \> (assoc state :state :group, :c c)
               ;; :else
               (-> state
                   (update :garbage inc)
                   (assoc :c c)))

    :ignore   (assoc state :state :garbage, :c c)

    ;; :else
    (throw-invalid-transition state c)))
