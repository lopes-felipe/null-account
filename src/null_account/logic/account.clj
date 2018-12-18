(ns null-account.logic.account
  (:import [java.util UUID]))

(defn new-account [name email]
  {:account/id (UUID/randomUUID)
   :account/name name
   :account/email email})