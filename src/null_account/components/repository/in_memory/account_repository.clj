(ns null-account.components.repository.in-memory.account-repository
  (:require [null-account.protocols.account-repository :as protocols.account-repository]
            [com.stuartsierra.component :as component]))

(defrecord InMemoryAccountRepository []

  component/Lifecycle
  (start [this] (assoc this :atom-map (atom {})))
  (stop  [this] (dissoc this :atom-map))

  protocols.account-repository/AccountRepository
  (get-account [this account-id]
    (let [atom-map (:atom-map this)]
      (@atom-map account-id)))

  (create-account! [this account]
    (let [atom-map (:atom-map this)]
      (swap! atom-map #(assoc % (:account/id account) account)))))
