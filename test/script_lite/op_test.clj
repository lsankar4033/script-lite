(ns script-lite.op-test
  (:require [clojure.test :refer :all]
            [script-lite.op :refer :all]))

(deftest exec-op-test

  (testing "failure short-circuit"
    (is (exec-op :op-1 nil)
        {:failed true, :stack nil}))

  (testing "OP_1 and OP_TRUE"
    (is (exec-op :op-1 nil)
        {:failed false, :stack `(1)})
    (is (exec-op :op-1 `(2))
        {:failed false, :stack `(1, 2)})

    (is (exec-op :op-true nil)
        {:failed false, :stack `(1)})
    (is (exec-op :op-true `(2))
        {:failed false, :stack `(1, 2)}))

  (testing "OP_DUP"
    (is (exec-op :op-dup nil)
        {:failed false, :stack nil})
    (is (exec-op :op-dup `(5))
        {:failed false, :stack `(5 5)}))

  (testing "fails for nonexistent op"
    (is (exec-op :op-fake nil)
        {:failed true, :stack nil})
    (is (exec-op :op-fake `(5))
        {:failed true, :stack `(5)})))
