(ns null-account.services.account
  (:require [null-account.controllers.account :as controller]
            [null-account.components.repository.in-memory-account-repository :refer :all]
            [com.stuartsierra.component :as component]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema Account
  {:name s/Str
   :email s/Str})

(def fake-storage (component/start (->InMemoryAccountRepository)))

(defn- get-account [id]
  (controller/get-account fake-storage id))

(defn create-account
  [account]
  (let [{:keys [name email]} account
        account (controller/create-account! fake-storage name email)]
    account))

(def account-routes
  (routes
   (context "/accounts" []
     (GET "/:id" []
       :path-params [id :- java.util.UUID]
       :summary "Retrieve Account by ID"
       (ok (get-account id)))
     (POST "/" []
       :body [account Account]
       :summary "Creates a new Account"
       (ok (create-account account))))))
