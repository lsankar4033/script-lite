(ns script-lite.core)

;; TODO: when I add crypto, sig+pubkey are stuck onto the stack *first*!


(defn exec-op
  "Run op with the given stack and return map of form {:failed, :stack}"
  [{:keys [op input]} {:keys [failed stack]}]
  (if failed
    {:failed failed, :stack stack}

    (case op
      (:op-1, :op-true) {:failed failed, :stack (conj stack 1)})))

(defn exec
  "Run the specified ops. Returns true or false"
  [ops]
  (let [{:keys [failed stack]} (reduce exec-op
                                       {:failed false, :stack []}
                                       ops)]

    ; This triple nesting is kinda gross...
    (when-not failed
      (when-let [head (first stack)]
        (= head 1)))))
