(ns null-account.components.http-kit
  (:require [null-account.handler :refer [app]]
            [org.httpkit.server :as httpkit]
            [compojure.api.middleware :refer [wrap-components]]
            [com.stuartsierra.component :as component]))

(defrecord HttpKit []
  component/Lifecycle
  (start [this]
    (println "Server started at http://localhost:3002")
    (assoc this :http-kit (httpkit/run-server
                           (wrap-components
                            #'app
                            (select-keys this [:account-repository]))
                           {:port 3002})))
  (stop [this]
    (if-let [http-kit (:http-kit this)]
      (http-kit))
    (dissoc this :http-kit)))