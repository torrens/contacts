(ns contacts.core
  (:use [contacts.contact]))

;; Drop Contacts collection
(drop-contacts)

;; Create Contacts with simple insert
(create-contact "Tony" "Blair")
(create-contact "Gordon" "Black")

;; Create Contact and return document as a map
(create-and-return-contact "Margaret" "Thatcher")

;; Sometimes users want to see an easy to identify reference number rather than an ObjectId
(create-contact-with-counter "Winston" "Churchill")

;; Find one contact by last name 
(find-contact-by-last-name "Thatcher")

;; Find many contacts by last names that begin with
(find-contacts-by-last-name-prefix "B")

;; Update contact details
(let [gordon-black (find-contact-by-last-name "Black")]
  (update-contact (:_id gordon-black) "Gordon" "Brown" ))

;; Update contact age
(let [gordon-brown (find-contact-by-last-name "Brown")
      tony-blair (find-contact-by-last-name "Blair")]
  (update-contact-age (:_id gordon-brown) 61)
  (update-contact-age (:_id tony-blair) 59))

;; Find contacts older than 55
(find-oap-contacts)

;; Delete Contact
(let [winston-churchill (find-contact-by-last-name "Churchill")]
  (delete-contact (:_id winston-churchill)))










