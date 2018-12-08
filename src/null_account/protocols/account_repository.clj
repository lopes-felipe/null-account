(ns null-account.protocols.account-repository)

(defprotocol AccountRepository
  "Protocol for Account's repository"
  (get-account     [this account-id] "Retrieves the Account based on its Id")
  (create-account! [this account]   "Creates a new Account"))