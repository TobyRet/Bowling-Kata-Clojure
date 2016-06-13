(ns clojure-bowling-kata.bowling-test
  (:require [clojure.test :refer :all]
            [clojure-bowling-kata.bowling :as c]))

(deftest bowling-test
  (testing "Calculates a normal score"
    (is (= 90 (c/score "98765432109876543210"))))

  (testing "Calculated perfect game"
    (is (= 300 (c/score "XXXXXXXXXXXX")))))
