(ns null-account.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [null-account.controllers.account :as controllers.account]))

(def app
  (api
   {:swagger
    {:ui "/"
     :spec "/swagger.json"
     :data {:info {:title "Null Account"
                   :description "Basic Accounting API"}}}}

   account/account-routes))
