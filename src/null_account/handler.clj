(ns null-account.handler
  (:require [compojure.route :as route]
            [compojure.api.sweet :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [null-account.services.account :as account]))

(def app
  (api
   {:swagger
    {:ui "/"
     :spec "/swagger.json"
     :data {:info {:title "Null Account"
                   :description "Basic Accounting API"}}}}

   account/account-routes))
