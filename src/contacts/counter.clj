(ns contacts.counter
  (:use [monger.core :only [connect! set-db! get-db]]
        [monger.collection :only [insert find-and-modify]]
        [monger.operators]))

;; Connect to database
(connect!)
(set-db! (get-db "contacts"))

(defn next-contact-number
  []
  (let [next-counter (find-and-modify "counter" {:_id "contactCounter"} {$inc {:counterValue 1}} :upsert true :return-new true)]
    (:counterValue next-counter)))