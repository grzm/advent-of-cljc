(def project 'com.grzm.advent-of-code)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"resources" "src/main"}
          :dependencies   '[[adzerk/boot-cljs "2.1.4" :scope "test"]
                            [adzerk/boot-test "RELEASE" :scope "test"]
                            [crisptrutski/boot-cljs-test "0.3.5-SNAPSHOT" :scope "test"]
                            [criterium "0.4.4"]
                            [metosin/boot-alt-test "0.3.2" :scope "test"]
                            [onetom/boot-lein-generate "0.1.3" :scope "test"]
                            [org.clojure/clojure "RELEASE"]
                            [org.clojure/clojurescript "1.9.946"]])

(task-options!
  pom {:project     project
       :version     version
       :description "Advent of Code solutions"
       :url         "https://github.com/grzm/advent-of-cljc"
       :scm         {:url "https://github.com/grzm/advent-of-cljc"}
       :license     {"MIT"
                     "https://opensource.org/licenses/MIT"}})

(deftask build
  "Build and install the project locally."
  []
  (comp (pom) (jar) (install)))

(require '[adzerk.boot-cljs :refer [cljs]])
(require '[adzerk.boot-test :refer [test]])
(require '[metosin.boot-alt-test :refer [alt-test]])
(require '[crisptrutski.boot-cljs-test :refer [test-cljs]])

(require 'boot.lein)
(boot.lein/generate)
