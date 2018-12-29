(ns null-account.components.repository.in-memory.transaction-repository-test
  (:require [null-account.protocols.transaction-repository :as protocols.transaction-repository]
            [null-account.components.repository.in-memory.transaction-repository :refer :all]
            [clojure.test :refer :all]
            [com.stuartsierra.component :as component]))

(deftest test-repository []
  (testing "Component's Start"
    (let [repository (->InMemoryTransactionRepository)
          repository (component/start repository)
          atom-map   (:atom-map repository)]

      (is (some? atom-map))))

  (testing "Component's Stop"
    (let [repository (map->InMemoryTransactionRepository {:atom-map (atom {})})
          repository (component/stop repository)
          atom-map   (:atom-map repository)]

      (is (nil? atom-map))))

  (testing "Repository's create-transaction"
    (let [repository (map->InMemoryTransactionRepository {:atom-map (atom {})})
          transaction-id 123
          account-id     456
          operation      :credit
          amount         100.50]

      (protocols.transaction-repository/create-transaction! repository {:transaction/id         transaction-id
                                                                        :transaction/account-id account-id
                                                                        :transaction/operation  operation
                                                                        :transaction/amount     amount})

      (let [transactions (@(:atom-map repository) account-id)
            transaction  (first transactions)]
        (is (some? transactions))
        (is (= (count transactions) 1))
        (is (= (:transaction/id         transaction) transaction-id))
        (is (= (:transaction/account-id transaction) account-id))
        (is (= (:transaction/operation  transaction) operation))
        (is (= (:transaction/amount     transaction) amount)))))

  (testing "Repository's get-transactions"
    (let [new-transaction {:transaction/id         123
                           :transaction/account-id 456
                           :transaction/operation  :credit
                           :transaction/amount     100.50}
          atom        (atom {(:transaction/account-id new-transaction) [new-transaction]})
          repository  (map->InMemoryTransactionRepository {:atom-map atom})]

      (let [transactions (protocols.transaction-repository/get-transactions repository (:transaction/account-id new-transaction))
            transaction  (first transactions)]
        (is (some? transactions))
        (is (= (count transactions) 1))
        (is (= (:transaction/id        transaction) (:transaction/id new-transaction)))
        (is (= (:transaction/operation transaction) (:transaction/operation new-transaction)))
        (is (= (:transaction/amount    transaction) (:transaction/amount new-transaction)))))))