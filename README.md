# Bigeye backend code challenge

Prerequisites
-------------
You will need to have git and Java 8 installed.  Note that this code will only run on Java 8 specifically.

Background
----------

The purpose of this challenge is to demostrate one's ability to code up a REST API.  The
Server can be started with the command `./gradlew run`.  Feel free to test out the server to make
sure it works, but please don't start coding before the scheduled interview time.

Note that we are using Hibernate as an ORM for this scaffold.  If you prefer a different ORM library,
feel free to add that to the build.gradle and wire it up before the interview.

Tables
------

There is one table with records existing in the database: 
```
PERSON
FIELD  	    TYPE  	        NULL  KEY  	DEFAULT  
ID          INTEGER(10)	    NO	  PRI	(NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_CADDDCD7_06E0_4242_A535_6F0B08CACCA4")
NAME        VARCHAR(255)	NO	  NULL
STATUS      VARCHAR(255)	NO	  NULL
MANAGER_ID  INTEGER(10)     NO    NULL
SALARY      FLOAT           NO    NULL
```

This table tracks employees in a company. Note the recursive relationship; `manager_id` points to the `Employee` record for the employee's manager.

Existing endpoints
------------------

The following curl commands should return valid results:

`curl localhost:8000/health`

`curl localhost:8000/employees/1`
