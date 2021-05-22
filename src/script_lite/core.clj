(ns script-lite.core)

;; TODO: when I add crypto, sig+pubkey are stuck onto the stack *first*!


(defn exec-op
  "Run op with the given stack and return map of form {:failed, :stack}"
  [op {:keys [failed stack]}]
  (if failed
    {:failed failed, :stack stack}

    (case op
      (:op-1, :op-true) {:failed failed, :stack (conj stack 1)}

      :op-dup {:failed failed,
               :stack (if-let [head (first stack)]
                        (conj stack (first stack))
                        stack)})))

;; TODO: should work through a list of bytes b/c some bytestrings will correspond to other data that needs to
;; be put on the stack


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
