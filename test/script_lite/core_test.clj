(ns script-lite.core-test
  (:require [clojure.test :refer :all]
            [script-lite.core :refer :all]))

(deftest exec-op-test

  (testing "failure short-circuit"
    (let [op {:op :op-1, :input nil}
          failed-result {:failed true, :stack nil}]
      (is (exec-op op failed-result) failed-result)))

  (testing "OP_1 and OP_TRUE"
    (let [op-1 {:op :op-1, :input nil}
          op-true {:op :op-true, :input nil}
          result {:failed false, :stack nil}]
      (is (exec-op op-1 result)  {:failed false, :stack `(1)})

      (is (exec-op op-true result)  {:failed false, :stack `(1)}))))

;; TODO: exec test. may want to restructure things accordingly?
