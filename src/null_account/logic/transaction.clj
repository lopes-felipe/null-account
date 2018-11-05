(ns null-account.logic.transaction
  (:import [java.util UUID]))

(defn new-transaction [operation amount]
  {:id (UUID/randomUUID)
   :operation operation
   :amount amount})