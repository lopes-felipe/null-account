(ns null-account.services.account
  (:require [null-account.controllers.account :as account]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema Account
  {:name s/Str
   :email s/Str})

(defn fake-storage (component/start (->InMemoryAccountRepository)))

(defn- get-account [id]
  (account/get-account id))

(defn create-account
  [account]
  (let [{:keys [name email]} account
        account (controller/create-account! fake-storage name email)]
    (ring-resp/response {:account account})))

(def account-routes
  (routes
   (context "/accounts" []
     (GET "/:id" []
       :path-params [id :- Long]
       :return (s/maybe Account)
       :summary "Retrieve Account by ID"
       (ok (get-account id)))
     (POST "/" []
       :return Account
       :body [account Pizza]
       :summary "Creates a new Account"
       (ok (create-account account))))))
