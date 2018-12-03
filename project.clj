(defproject null-account "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]

                 [com.stuartsierra/component "0.3.2"]
                 [metosin/compojure-api "1.1.12"]
                 [metosin/ring-http-response "0.9.0"]]

  :plugins [[lein-ring "0.12.4"]]
  :ring {:handler null-account.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}}

  :main ^{:skip-aot true} null-account.server)
