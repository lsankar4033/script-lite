(ns script-lite.op)

(defn exec-op
  "Run op with the given stack and return map of form {:failed, :stack}"
  [op stack]
  ; TODO: this could be a good chance to experiment with a defop macro! Understand DSLs a bit more
  (case op
    (:op-1, :op-true) {:failed false, :stack (conj stack 1)}

    :op-dup {:failed false,
             :stack (if-let [head (first stack)]
                      (conj stack (first stack))
                      stack)}

    ; If no op match, fail with current stack
    {:failed true, :stack stack}))
