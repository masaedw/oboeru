(ns oboeru
  (:use compojure.core
        ring.adapter.jetty
        oboeru.core)
  (:require [compojure.route :as route]))

(defroutes main-routes
  (GET "/" [] (index))
  (GET "/new-test" [] (new-test))
  (GET "/random-test" [] (random-test))
  )

(load-words "data/words.clj")

;; interactive
(future (run-jetty (var main-routes) {:port 8080}))

;; static
;; (run-jetty main-routes {:port 8080})
