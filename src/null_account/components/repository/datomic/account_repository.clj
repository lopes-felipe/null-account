(ns null-account.components.repository.datomic.account-repository
  (:require [null-account.protocols.account-repository :as protocols.account-repository]
            [com.stuartsierra.component :as component]
            [datomic.api :only [q db] :as d]))

(defrecord DatomicAccountRepository [connection-string]
  component/Lifecycle
  (start [this]
    (assoc this :conn (d/connect connection-string)))

  (stop [this]
    (dissoc this :conn))

  protocols.account-repository/AccountRepository
  (get-account [this account-id]
    (let [conn (:conn this)
          results (d/q '[:find (pull ?e [*])
                         :in $ ?account-id
                         :where [?e :account/id ?account-id]]
                       (d/db conn), account-id)]
      (ffirst results)))

  (create-account! [this account]
    (let [conn (:conn this)]
      @(d/transact conn [account]))))