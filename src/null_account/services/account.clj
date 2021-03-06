(ns null-account.services.account
  (:require [null-account.util :as util]
            [null-account.controllers.account :as controller]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema Account
  {:name s/Str
   :email s/Str})

(defn- get-account [account-repository transaction-repository id]
  (let [account (controller/get-account account-repository transaction-repository id)]
    (util/nsmap->map account)))

(defn- create-account!
  [account-repository account]
  (let [{:keys [name email]} account
        account (controller/create-account! account-repository name email)]
    (util/nsmap->map account)))

(def account-routes
  (routes
   (context "/accounts" []
     :tags ["Account"]

     (GET "/:id" []
       :components [account-repository transaction-repository]
       :path-params [id :- java.util.UUID]
       :summary "Retrieve Account by ID"
       (ok (get-account account-repository transaction-repository id)))
     (POST "/" []
       :components [account-repository]
       :body [account Account]
       :summary "Creates a new Account"
       (ok (create-account! account-repository account))))))