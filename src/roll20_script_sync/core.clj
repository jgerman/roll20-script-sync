(ns roll20-script-sync.core
  (:require [carica.core :refer [config configurer resources overrider]]
            [clj-webdriver.taxi :refer :all]
            [clojure-watch.core :refer [start-watch]]
            [clojure.string :as str]))


(def driver (new-driver {:browser :firefox}))


(defn make-path [dir f]
  (cond (.endsWith "/" dir) (str dir f)
        :else (str dir "/" f)))

(defn get-script-tabs [driver]
  (elements driver "a[data-toggle=tab]"))

(defn script-tabs-to-dict [script-tabs]
  (into {} (map (fn [x] {(text x) x}) script-tabs)))

(defn extract-script-id [element]
  (-> (attribute element :href)
      (str/split #"#")
      (second)))

;; get a text area div#script-37306>textarea

(defn map-script-ids )

(defn -main [& args]
  ;; we'll clean this bit up later
  (let [monitor-dir (first args)
        config (configurer [(make-path (. System getProperty "user.home") ".roll20.edn")
                            (make-path monitor-dir "api-scripts.edn")])
        override-config (overrider config)
        campaign-url (config :campaign-url)
        files (config :files)]
    (to driver campaign-url) ;; really this one will take you to login
    (input-text driver "label+input[name=email]" (config :username))
    (input-text driver "label+input[name=password]" (config :password))
    (click driver "button.btn.btn-primary.calltoaction.btn-lg")
    (to driver campaign-url)
    #_(println (second (elements driver ".script, .tab-pane")))
    )

  )
