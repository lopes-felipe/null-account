(ns null-account.controllers.account
  (:require [null-account.logic.account :as account]
            [null-account.protocols.account-repository :as account-repository]))

(defn create-account [account-repository name email]
  (let [account (account/new-account name email)]
    (account-repository/create-account account-repository account)
    account))

(defn get-account [account-repository account-id]
  (let [account (account-repository/get-account account-repository account-id)]
    account))