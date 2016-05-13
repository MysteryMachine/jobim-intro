(ns intro.blog-test
  (:require [cljs.test :refer-macros [deftest is run-tests]]
            [intro.blog :as blog]
            [jobim.figwheel.helper])
  (:require-macros [jobim.core :as jobim]))

#_(deftest code-slide-test
  (let [{:keys [a b c %4]} (jobim/env blog/code-slide)]
    (is (= a 3))
    (is (= b 6))
    (is (= (c 1) 10))
    (is (= %4 19))))

(run-tests)
