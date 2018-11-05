(ns null-account.server
  (:gen-class)
  (:require [null-account.components.repository.in-memory-account-repository :as repository.in-memory-account-repository]))

(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (println "\nCreating your server...")
  (repository.in-memory-account-repository/test))

