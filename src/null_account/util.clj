(ns null-account.util)

(defn nsmap->map
  "Removes the map's keywords namespaces"
  [m]
  (reduce-kv (fn [m k v]
               (let [new-kw (keyword (name k))]
                 (assoc m new-kw v)))
             {} m))