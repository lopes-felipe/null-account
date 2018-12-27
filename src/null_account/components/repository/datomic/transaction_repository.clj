(ns null-account.components.repository.datomic.transaction-repository
  (:require [null-account.protocols.transaction-repository :as protocols.transaction-repository]
            [com.stuartsierra.component :as component]
            [datomic.api :only [q db] :as d]))

(defrecord DatomicTransactionRepository [connection-string]

  component/Lifecycle
  (start [this]
    (assoc this :conn (d/connect connection-string)))

  (stop [this]
    (dissoc this :conn))

  protocols.transaction-repository/TransactionRepository
  (get-transactions [this account-id]
    (let [conn (:conn this)
          results (d/q '[:find (pull ?e [*])
                         :in $ ?account-id
                         :where [?e :transaction/account-id ?account-id]]
                       (d/db conn), account-id)]
      (flatten results)))

  (create-transaction! [this transaction]
    (let [conn (:conn this)]
      @(d/transact conn [transaction]))))