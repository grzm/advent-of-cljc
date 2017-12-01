(def project 'com.grzm.advent-of-code)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"resources" "src/main"}
          :dependencies   '[[adzerk/boot-test "RELEASE" :scope "test"]
                            [metosin/boot-alt-test "0.3.2" :scope "test"]
                            [onetom/boot-lein-generate "0.1.3" :scope "test"]
                            [org.clojure/clojure "RELEASE"]
                            [org.clojure/clojurescript "1.9.946"]
                            [tubular "1.1.1" :scope "test"]])

(task-options!
 pom {:project     project
      :version     version
      :description "Advent of Code solutions"
      :url         "https://github.com/yourname/com.grzm.advent-of-code"
      :scm         {:url "https://github.com/yourname/com.grzm.advent-of-code"}
      :license     {"MIT"
                    "https://opensource.org/licenses/MIT"}})

(deftask build
  "Build and install the project locally."
  []
  (comp (pom) (jar) (install)))

(require '[adzerk.boot-test :refer [test]])

(require 'boot.lein)
(boot.lein/generate)

(require '[metosin.boot-alt-test :refer [alt-test]])

