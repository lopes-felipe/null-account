(ns null-account.server
  (:gen-class)
  (:require [null-account.components :as components]
            [reloaded.repl :refer [go set-init!]]))

(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (let [env (keyword (first args))]
    (set-init! #(components/new-system env))
    (go)))