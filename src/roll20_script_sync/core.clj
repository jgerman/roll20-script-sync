(ns roll20-script-sync.core
  (:require [carica.core :refer [configurer resources overrider]]
            [clj-webdriver.taxi :refer :all]
            [clojure-watch.core :refer [start-watch]]))


(def config (configurer "test.edn"))
(def override-config (overrider config))
(def driver (new-driver {:browser :firefox}))


(defn make-path [dir f]
  (cond (.endsWith "/" dir) (str dir f)
        :else (str dir "/" f)))

(defn -main [& args]
  ;; we'll clean this bit up later
  (let [monitor-dir (first args)
        config (configurer (make-path monitor-dir "api-scripts.edn"))
        override-config (overrider config)
        campaign-url (config :campaign-url)
        files (config :files)]
    (to driver campaign-url)
    (println "Hit enter when logged in...")
    (read-line)
    (to driver campaign-url)
    )

  )
