(ns script-lite.core)

;; op structure = {:op :input}
;; maybe i start by just writing a program that processes OP_PUSH, OP_ADD, OP_EQUAL, OP_VERIFY?
;; what is scriptSig? Maybe don't need to worry about that for now
;; TODO: when I add crypto, sig+pubkey are stuck onto the stack *first*!

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

(defn exec-op
  "Run op with the given stack and return map of form {:failed, :stack}"
  [op {:keys [failed stack]}]
  (if failed
    {:failed failed, :stack stack}

    ; TODO: run op
    ))
