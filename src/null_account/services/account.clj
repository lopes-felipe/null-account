(ns null-account.services.account
  (:require [null-account.controllers.account :as controller]
            [com.stuartsierra.component :as component]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema Account
  {:name s/Str
   :email s/Str})

(defn- get-account [repository id]
  (controller/get-account repository id))

(defn create-account
  [repository account]
  (let [{:keys [name email]} account
        account (controller/create-account! repository name email)]
    account))

(def account-routes
  (routes
   (context "/accounts" []
     (GET "/:id" []
       :components [repository]
       :path-params [id :- java.util.UUID]
       :summary "Retrieve Account by ID"
       (ok (get-account repository id)))
     (POST "/" []
       :components [repository]
       :body [account Account]
       :summary "Creates a new Account"
       (ok (create-account repository account))))))
