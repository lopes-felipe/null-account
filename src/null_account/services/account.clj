(ns null-account.services.account
  (:require [null-account.controllers.account :as controllers.account]
            [ring.util.response :as ring-resp]))

(defroutes routes
  (GET "/accounts/:id" [id] (get-account)))

(defn create-account
  [{{:keys [customer-id]} :edn-params
    {:keys [http storage]} :components}]
  (let [account (controller/create-account! customer-id storage http)]
    (ring-resp/response {:account account})))

(defn- get-account [id] 
  (controllers.account/get-account id)