(ns contacts.contact
  (:use [monger.core :only [connect! set-db! get-db]]
        [monger.collection :only [insert insert-and-return find-one-as-map find-maps find-and-modify update remove-by-id drop]]
        [monger.operators]
        [contacts.counter])
  (:import [org.bson.types ObjectId]))

;; Connect to database
(connect!)
(set-db! (get-db "contacts"))

;; Document name
(def doc "contact")

;; Create ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn create-contact
  [first-name last-name]
  (insert doc {:firstName first-name :lastName last-name}))

(defn create-and-return-contact
  [first-name last-name]
  (insert-and-return doc {:firstName first-name :lastName last-name}))

(defn create-contact-with-counter
  [first-name last-name]
  (insert-and-return doc {:firstName first-name :lastName last-name :counter (next-contact-number)}))

;; Retrieve ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn find-contact-by-id
  [contact-id]
  (find-by-id doc contact-id))

(defn find-contact-by-last-name
  [last-name]
  (find-one-as-map doc {:lastName last-name}))

(defn find-contacts-by-last-name
  [last-name]
  (find-maps doc {:lastName last-name}))

(defn find-contacts-by-last-name-prefix
  [last-name]
  (find-maps doc {:lastName {$regex (str last-name "*")}}))

(defn find-oap-contacts
  []
  (find-maps doc {:age {"$gt" 55}}))

;; Update ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;::

(defn update-contact
  [contact-id first-name last-name]
  (update doc {:_id contact-id} {:firstName first-name :lastName last-name}))

(defn update-contact-age
  [contact-id age]
  (update doc {:_id contact-id} {$set {:age age}}))

;; Delete ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;::

(defn delete-contact
  [contact-id]
  (remove-by-id doc contact-id))

;; Drop Collection
(defn drop-contacts
  []
  (drop doc))

