(ns null-account.controllers.account
  (:require [null-account.logic.account :as account]
            [null-account.logic.transaction :as transaction]
            [null-account.protocols.account-repository :as account-repository]
            [null-account.controllers.transaction :as controllers.transaction]))

(defn create-account! [account-repository name email]
  (let [account (account/new-account name email)]
    (account-repository/create-account! account-repository account)
    account))

(defn get-account [account-repository transaction-repository account-id]
  (let [account (future (account-repository/get-account account-repository account-id))
        transactions (future (controllers.transaction/get-transactions transaction-repository account-id))]
    (println (str "Account: " @account))
    (assoc @account
           :account/transactions @transactions
           :account/balance-due (transaction/calculate-balance-due @transactions))))