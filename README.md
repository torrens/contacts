Contacts
========

A very simple project to demonstrate how easy it is to use Monger with MongoDB.  [Monger](https://github.com/michaelklishin/monger/) is a Clojure library which makes using MongoDB a doddle.

Requirements
------------

You will need Leiningen and a local instance of MongoDB.

Contents
--------

- contact.clj has the majority of code to perform data manipulation.
- counter.clj has one method to update a counter.
- core.clj has some code which you can run in your REPL.

Connect
-------

Connect to a MongoDB instance using connect!
Set the current database by using set-db!

	(connect!)
	(set-db! (get-db "contacts"))

Create
------

Douments are added to the database using insert.  

	(defn create-contact
		[first-name last-name]
		(insert doc {:firstName first-name :lastName last-name}))

To return a map of the document inserted, use insert-and-return.

	(defn create-and-return-contact
		[first-name last-name]
		(insert-and-return doc {:firstName first-name :lastName last-name}))

Retrieve
--------

Probably most of your work in any database code is spent writing database query functions.  Monger/MongoDB has lots of great modifiers that you can use for this.

To find a document by id use find-by-id.

	(defn find-contact-by-id
		[contact-id]
		(find-by-id doc contact-id))
	
To find a document by a document field and return a map, use find-one-as-map.

	(defn find-contacts-by-last-name
		[last-name]
		(find-maps doc {:lastName last-name}))

To find a document using a regular expression which equates to a SQL Like command, use the $regex operator.

	(defn find-contacts-by-last-name-prefix
		[last-name]
		(find-maps doc {:lastName {$regex (str last-name "*")}}))

Update
------

Update functionality in MongoDB is destructive.  If you update an existing document, only the fields you pass to the update function will be stored in the database.  To update only a few fields use the $set modifier.

	(defn update-contact-age
		[contact-id age]
		(update doc {:_id contact-id} {$set {:age age}}))
	
Delete
------

Delete documents using the remove-by-id function.

	(defn delete-contact
		[contact-id]
		(remove-by-id doc contact-id))
	

