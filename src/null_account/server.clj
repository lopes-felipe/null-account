(ns null-account.server
  (:gen-class)
  (:require [org.httpkit.server :as httpkit]
            [compojure.api.middleware :refer [wrap-components]]
            [com.stuartsierra.component :as component]
            [null-account.handler :refer [app]]
            [reloaded.repl :refer [go set-init!]]))

(defrecord Example []
  component/Lifecycle
  (start [this]
    (assoc this :example "hello world"))
  (stop [this]
    this))

(defrecord HttpKit []
  component/Lifecycle
  (start [this]
    (println "Server started at http://localhost:3002")
    (assoc this :http-kit (httpkit/run-server
                           (wrap-components
                            #'app
                            (select-keys this [:example]))
                           {:port 3002})))
  (stop [this]
    (if-let [http-kit (:http-kit this)]
      (http-kit))
    (dissoc this :http-kit)))

(defn new-system []
  (component/system-map
   :example (->Example)
   :http-kit (component/using (->HttpKit) [:example])))

(defn -main []
  (set-init! #(new-system))
  (go))