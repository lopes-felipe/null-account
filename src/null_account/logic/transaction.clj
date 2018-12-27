(ns null-account.logic.transaction
  (:import [java.util UUID]))

(defn new-transaction [account-id operation amount]
  {:transaction/id (UUID/randomUUID)
   :transaction/account-id account-id
   :transaction/operation (keyword operation)
   :transaction/amount (float amount)})

(defn calculate-balance-due [transactions]
  "Calculates the account's balance due based on its transactions"
  (reduce
   (fn [balance-due transaction]
     (if (= (:transaction/operation transaction) :operation/credit)
       (+ balance-due (:transaction/amount transaction))
       (- balance-due (:transaction/amount transaction))))
   0 transactions))