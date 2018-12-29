(ns null-account.services.transaction
  (:require [null-account.util :as util]
            [null-account.controllers.transaction :as controller]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema Transaction
  {:account-id s/Uuid
   :operation (s/enum :credit :debit)
   :amount s/Num})

(defn- create-transaction! [transaction-repository transaction]
  (let [{:keys [account-id operation amount]} transaction
        transaction (controller/create-transaction! transaction-repository account-id operation amount)]
    (util/nsmap->map transaction)))

(def transaction-routes
  (routes
   (context "/transactions" []
     :tags ["Transaction"]

     (POST "/" []
       :components [transaction-repository]
       :body [transaction Transaction]
       :summary "Creates a new transaction for a given account"
       (ok (create-transaction! transaction-repository transaction))))))