# null-account

A simple Clojure API represeting a really basic banking account service.

## Architecture

### Ports and Adapters Architecture

This API is organized based on the ["ports and adapters" architecture](https://fideloper.com/hexagonal-architecture), a.k.a "hexagonal" architecture .

### Components

This API uses the [components](https://github.com/stuartsierra/component) abstraction to organize its `ports` (e.g. HTTP client, datomic client) and any other logic that needs to track mutable state or encode dependencies between stateful components.

### Swagger

This API is compliant to the Swagger specification and provides a test UI for validation purposes on its root path.

### Storage

This API provides two storage mechanism types: a local (in memory) one and a Datomic based one.

#### Config

In order to use the Datomic storage repository, a valid URI for a running Datomic database must be provided on the config/app_setting.edn configuration file.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running the server

1. Start the application `lein run` (or `lein run local` for the local/in-memory version)
2. Go to [http://localhost:3002/](http://localhost:3002/) to see the Swagger testing UI

## Test

This API uses the standard Clojure testing framework. 

In order to execute the tests, run: `lein test`.
