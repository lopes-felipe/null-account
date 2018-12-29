(ns null-account.logic.transaction-test
  (:require [null-account.logic.transaction :as logic.transaction]
            [clojure.test :refer :all]))

(deftest test-transaction-logic
  (testing "Logic's new transaction"
    (let [account-id  123
          operation   :credit
          amount      100.50
          transaction (logic.transaction/new-transaction account-id operation amount)]

      (is (some? transaction))
      (is (some? (:transaction/id transaction)))
      (is (= (:transaction/account-id transaction) account-id))
      (is (= (:transaction/operation  transaction) operation))
      (is (= (:transaction/amount     transaction) amount))))

  (testing "Logic's balance due calculation"
    (let [transactions [{:transaction/id         123
                         :transaction/account-id 456
                         :transaction/operation  :operation/credit
                         :transaction/amount     100.50}
                        {:transaction/id         789
                         :transaction/account-id 456
                         :transaction/operation  :operation/debit
                         :transaction/amount     50}]
          balance-due  (logic.transaction/calculate-balance-due transactions)]

      (is (= balance-due 50.50)))))