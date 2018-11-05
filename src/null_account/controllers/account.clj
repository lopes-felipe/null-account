(ns null-account.controllers.account
  (:require [null-account.logic.account :as logic.account]
            [null-account.protocols.account-repository :as protocols.account-repository]))

(defn create-account [account-repository name email]
  (let [account (logic.account/new-account name email)]
    (protocols.account-repository/create-account account-repository account)
    account))

(defn get-account [account-repository account-id]
  (let [account (protocols.account-repository/get-account account-repository account-id)]
    account))