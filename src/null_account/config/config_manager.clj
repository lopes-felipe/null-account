(ns null-account.config.config-manager
  (:require [clojure.edn :as edn]))

(defn load-config []
  (edn/read-string (slurp "config/app_settings.edn")))