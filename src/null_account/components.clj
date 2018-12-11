(ns null-account.components
  (:require [null-account.components.repository.in-memory-account-repository :refer :all]
            [null-account.components.http-kit :as http-kit]
            [com.stuartsierra.component :as component]))

(defn new-system []
  (component/system-map
   :repository (->InMemoryAccountRepository)
   :http-kit (component/using (http-kit/->HttpKit) [:repository])))