(ns null-account.components.repository.in-memory.account-repository-test
  (:require [null-account.protocols.account-repository :as protocols.account-repository]
            [null-account.components.repository.in-memory.account-repository :refer :all]
            [clojure.test :refer :all]
            [com.stuartsierra.component :as component]))

(deftest test-repository []
  (testing "Component's Start"
    (let [repository (->InMemoryAccountRepository)
          repository (component/start repository)
          atom-map   (:atom-map repository)]

      (is (some? atom-map))))

  (testing "Component's Stop"
    (let [repository (map->InMemoryAccountRepository {:atom-map (atom {})})
          repository (component/stop repository)
          atom-map   (:atom-map repository)]

      (is (nil? atom-map))))

  (testing "Repository's create-account"
    (let [repository (map->InMemoryAccountRepository {:atom-map (atom {})})
          account-id 123
          name       "Felipe Lopes"
          email      "lopes_felipe @icloud.com"]

      (protocols.account-repository/create-account! repository {:account/id account-id
                                                                :account/name name
                                                                :account/email email})

      (let [account (@(:atom-map repository) account-id)]
        (is (some? account))
        (is (= (:account/name account) name))
        (is (= (:account/email account) email)))))

  (testing "Repository's get-account"
    (let [new-account {:account/id 123
                       :account/name "Felipe Lopes"
                       :account/email "lopes_felipe @icloud.com"}
          atom        (atom {(:account/id new-account) new-account})
          repository  (map->InMemoryAccountRepository {:atom-map atom})]

      (let [account (protocols.account-repository/get-account repository (:account/id new-account))]
        (is (some? account))
        (is (= (:account/name account) (:account/name new-account)))
        (is (= (:account/email account) (:account/email new-account)))))))