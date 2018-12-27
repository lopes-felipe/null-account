(ns null-account.util)

(defn nsmap->map
  "Removes the map's keywords namespaces"
  [m]
  (if (sequential? m)
    (map nsmap->map m)

    (if (map? m)
      (reduce-kv (fn [m k v]
                   (let [new-kw (keyword (name k))]
                     (assoc m new-kw (nsmap->map v))))
                 {} m)
      m)))