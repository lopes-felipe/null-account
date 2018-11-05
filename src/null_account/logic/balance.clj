(ns null-account.logic.balance
  (:require [null-account.logic.transaction :as transaction]))

(defn new-balance []
  {:balance-due 0
   :transactions []})

(defn calculate-balance-due [transactions]
  "Calculates the account's balance due based on its transactions"
  (reduce
   (fn [balance-due transaction]
     (if (= (:operation transaction) :credit)
       (+ balance-due (:amount transaction))
       (- balance-due (:amount transaction))))
   0 transactions))