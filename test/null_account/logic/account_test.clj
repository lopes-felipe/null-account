(ns null-account.logic.account-test
  (:require [null-account.logic.account :as logic.account]
            [clojure.test :refer :all]))

(deftest test-account-logic []
  (testing "Logic's new-account"
    (let [name    "Felipe Lopes"
          email   "lopes_felipe@icloud.com"
          account (logic.account/new-account name email)]

      (is (some? account))
      (is (some? (:account/id account)))
      (is (= (:account/name account) name))
      (is (= (:account/email account) email)))))