(ns script-lite.core
  (:require [script-lite.op :as op]))

;; TODO: when I add crypto, sig+pubkey are stuck onto the stack *first*!

(defn exec-ops
  [ops]
  (reduce op/exec-op
          {:failed false, :stack []}
          (fn [acc op]
            (if (:failed acc)
              (reduced acc)
              (op/exec-op op (:stack acc))))))

(defn exec
  "Run the specified ops. Returns true or false. Doesn't currently support non-op bytes in the script."
  [ops]
  ; TODO: should work through a list of bytes b/c some bytestrings will correspond to other data that needs to
  ; be put on the stack
  (let [{:keys [failed stack]} exec-ops]

    ; This triple nesting is kinda gross...
    (when-not failed
      (when-let [head (first stack)]
        (= head 1)))))
