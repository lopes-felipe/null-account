(ns null-account.components
  (:require [null-account.components.http-kit :as http-kit]
            [null-account.config.config-manager :as config-manager]
            [null-account.components.repository.datomic.account-repository :refer :all]
            [null-account.components.repository.in-memory.account-repository :refer :all]
            [com.stuartsierra.component :as component]))

(defn get-connection-string []
  (let [config (config-manager/load-config)]
    (get-in config [:database :connection-string])))

(defn base-system []
  (component/system-map
   :account-repository (->DatomicAccountRepository (get-connection-string))
   :http-kit (component/using (http-kit/->HttpKit) [:account-repository])))

(defn local-system []
  (merge (base-system)
         (component/system-map
          :account-repository (->InMemoryAccountRepository))))

(def systems
  {:local (local-system)
   :base (base-system)})

(defn new-system
  ([] (new-system :base))
  ([env] (get systems env (:base systems))))