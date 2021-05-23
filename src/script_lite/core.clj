(ns script-lite.core
  (:require [script-lite.op :as op]))

(defn exec-ops
  [ops]
  (reduce (fn [acc op]
            (if (:failed acc)
              (reduced acc)
              (op/exec-op op (:stack acc))))

          {:failed false, :stack []}

          ops))

(defn exec
  "Run the specified ops. Returns true or false. Doesn't currently support non-op bytes in the script."
  [ops]
  (let [{:keys [failed stack]} exec-ops]

    ; This triple nesting is kinda gross...
    (when-not failed
      (when-let [head (first stack)]
        (= head 1)))))
