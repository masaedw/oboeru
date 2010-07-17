(ns oboeru
  (:use compojure.core
        ring.adapter.jetty
        oboeru.core)
  (:require [compojure.route :as route]))

(defroutes main-routes
  (GET "/" [] "<h1>Hello World</h1>"))

(run-jetty main-routes {:port 8080})
