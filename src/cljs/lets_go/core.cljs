(ns lets-go.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [lets-go.events :as events]
            [lets-go.routes :as routes]
            [lets-go.views :as views]
            [lets-go.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
