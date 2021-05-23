(ns script-lite.op)

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
