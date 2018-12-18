(ns null-account.logic.transaction
  (:import [java.util UUID]))

(defn new-transaction [operation amount]
  {:id (UUID/randomUUID)
   :operation operation
   :amount amount})

(defn calculate-balance-due [transactions]
  "Calculates the account's balance due based on its transactions"
  (reduce
   (fn [balance-due transaction]
     (if (= (:operation transaction) :credit)
       (+ balance-due (:amount transaction))
       (- balance-due (:amount transaction))))
   0 transactions))