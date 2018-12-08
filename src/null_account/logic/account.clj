(ns null-account.logic.account
  (:import [java.util UUID])
  (:require [null-account.logic.balance :as balance]))

(defn new-account [name email]
  {:id (UUID/randomUUID)
   :name name
   :email email
   :balance (balance/new-balance)})