(ns null-account.components.repository.in-memory.transaction-repository
  (:require [null-account.protocols.transaction-repository :as protocols.transaction-repository]
            [com.stuartsierra.component :as component]))

(defrecord InMemoryTransactionRepository []

  component/Lifecycle
  (start [this] (assoc this :atom-map (atom {})))
  (stop  [this] (dissoc this :atom-map))

  protocols.transaction-repository/TransactionRepository
  (get-transactions [this account-id]
    (let [atom-map (:atom-map this)]
      (@atom-map account-id)))

  (create-transaction! [this transaction]
    (let [atom-map (:atom-map this)
          account-id (:transaction/account-id transaction)]
      (swap! atom-map #(assoc % account-id (conj (% account-id) transaction))))))