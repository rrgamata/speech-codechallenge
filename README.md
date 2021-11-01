# speech-codechallenge
 A simple speech API for code challenge

This is a simple API that simulates the backend part of a speech workflow system.

Functions.

- Create a speech - creates a new Speech Object using JSON 
- Get all speeches - gets all speeches (possible upgrades implement pagination) 
- Search speech by author - currectly searches exact author's name 
- Search speech by tag - implemented 1:n relationship for future use might change it to another object 
- Search speech between 2 dates 
- Search speech via snippets - used llike to get snippets possible upgrade use FTS for search 
- Remove speech - removes speech from DB
- Delete speech - sets delete flag to 1 rather than completely removing data.

---

Access
======

To access the working app goto: https://codechallenge-speechapi.herokuapp.com/api/v1/codechallenge


---

SAMPLE CURL COMMANDS
====================

Please see the curl commands below to access the functions

GET
===

### GETALL (Including deleted)

curl http://localhost:8080/api/v1/codechallenge/

### GETALL ACTIVE (deleted files not included)

curl http://localhost:8080/api/v1/codechallenge/active

### GET ALL with Author

curl -G "http://localhost:8080/api/v1/codechallenge/author" --data-urlencode "value=Nelson Mandela"


### GET ALL with Tags

curl -G "http://localhost:8080/api/v1/codechallenge/tag" --data-urlencode "value=motivational"


### GET ALL Between Dates

curl -G "http://localhost:8080/api/v1/codechallenge/between" --data-urlencode "before=1963-01-17" --data-urlencode "after=1964-11-20"


### GET ALL speeches with snip
curl -G "http://localhost:8080/api/v1/codechallenge/search" --data-urlencode "snip=I have a dream"




---

EDIT/PUT 
========

### Author 

curl -X PUT http://localhost:8080/api/v1/codechallenge/edit/1 -d "author=JANE DOE"


### Speech Body

curl -X PUT http://localhost:8080/api/v1/codechallenge/edit/1 -d "body=speech body edited text"

### Tags/ Keywords

curl -X PUT http://localhost:8080/api/v1/codechallenge/edit/1 -d "tags=EDITED,tags,Example"

### Date

curl -X PUT http://localhost:8080/api/v1/codechallenge/edit/1 -d "date=2021-08-10"


### Multiple edits (example edit both body and tags)

curl -X PUT http://localhost:8080/api/v1/codechallenge/edit/1 -d "body=speech body edited text" -d "tags=EDITED,tags,Example"

---

ADD/POST
========

### POST
curl -d "{  \"speechAuthor\": \"Nelson Mandela\",\"speechBody\": \"The road ahead will be long. Our climb will be steep. We may not get there in one year, or even one term, but America — I have never been more hopeful than I am tonight that we will get there. I promise you: We as a people will get there.\", \"speechKeywords\": \"America, President\", \"speechDate\": \"2008-11-05\"}"  -H "Content-Type: application/json" http://localhost:8080/api/v1/codechallenge/ 


---

Afterword.
==========

For my very first API this is a great achievement for me.

There will probably be bugs and a lot more optimization needed but I'm proud to be able to make this in 1 week.

For any comments or suggestions kindly contact me via [Linkedin](https://www.linkedin.com/in/rufusamata/) 

Thank you for any input you might give.
