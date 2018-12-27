(ns null-account.handler
  (:require [compojure.route :as route]
            [compojure.api.sweet :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [null-account.services.account :as account]
            [null-account.services.transaction :as transaction]))

(def app
  (api
   {:swagger
    {:ui "/"
     :spec "/swagger.json"
     :data {:info {:version "1.0.0"
                   :title "Null Account"
                   :description "Basic Accounting API"
                   :contact {:name "Felipe Lopes"
                             :email "lopes_felipe@icloud.com"}}
            :tags [{:name "Account", :description "Account endpoint"}
                   {:name "Transaction", :description "Transaction endpoint"}]}}}

   account/account-routes
   transaction/transaction-routes))
