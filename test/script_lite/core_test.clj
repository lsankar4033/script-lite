(ns script-lite.core-test
  (:require [clojure.test :refer :all]
            [script-lite.core :refer :all]))

(deftest exec-op-test

  (testing "failure short-circuit"
    (let [failed-result {:failed true, :stack nil}]
      (is (exec-op :op-1 failed-result) failed-result)))

  (testing "OP_1 and OP_TRUE"
    (let [result {:failed false, :stack nil}]
      (is (exec-op :op-1 result)  {:failed false, :stack `(1)})

      (is (exec-op :op-true result)  {:failed false, :stack `(1)})))

  (testing "OP_DUP"
    (let [empty-stack-result {:failed false, :stack nil}
          nonempty-stack-result {:failed false, :stack `(5)}]
      ; NOTE: current behavior is noop when empty stack
      (is (exec-op :op-dup empty-stack-result) empty-stack-result)

      (is (exec-op :op-dup nonempty-stack-result) {:failed false, :stack `(5 5)}))))

;; TODO: exec test. may want to restructure things accordingly?
