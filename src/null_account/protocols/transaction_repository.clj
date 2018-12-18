(ns null-account.protocols.transaction-repository)

(defprotocol
 "Protocol for Transaction's repository"
  (get-transactions    [this account-id]  "Retrieves the Transactions based on the AccountId")
  (create-transaction! [this transaction] "Creates a new Transaction"))