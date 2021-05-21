(ns script-lite.core)

;; op structure = {:op :input}
;; maybe i start by just writing a program that processes OP_ADD, OP_EQUAL, OP_VERIFY?
;; what is scriptSig? Maybe don't need to worry about that for now

(defn exec
  "Run the specified ops. Returns true or false"
  [ops]
  (let [{:keys [failed stack]} (reduce exec-op
                                       {:failed false, :stack []}
                                       ops)]
    (if failed
      false

      ; if script completes, is considered valid iff top of stack exists and == true
      (true? (first stack)))))

(defn exec-op
  "Run op with the given stack and return map of form {:failed, :stack}"
  [op {:keys [failed stack]}]
  (if failed
    {:failed failed, :stack stack}

    ; TODO: run op
    ))
