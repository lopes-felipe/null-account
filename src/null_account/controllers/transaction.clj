(ns null-account.controllers.transaction
  (:require [null-account.logic.transaction :as transaction]
            [null-account.protocols.transaction-repository :as transaction-repository]))

(defn create-transaction! [transaction-repository account-id operation amount]
  (let [transaction (transaction/new-transaction account-id operation amount)]
    (transaction-repository/create-transaction! transaction-repository transaction)
    transaction))

(defn get-transactions [transaction-repository account-id]
  (let [transactions (transaction-repository/get-transactions transaction-repository account-id)]
    (println (str "Transactions: " transactions))
    transactions))