(ns null-account.server
  (:gen-class)
  (:require [null-account.components :as components]
            [reloaded.repl :refer [go set-init!]]))


(defn -main []
  (set-init! #(components/new-system))
  (go))