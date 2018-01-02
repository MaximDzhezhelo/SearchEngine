# SearchEngine

The system has two pieces:
1. SearchEnginePortal    [Client](https://github.com/MaximDzhezhelo/SearchEnginePortal)
2. SearchEngineService   [Server](https://github.com/MaximDzhezhelo/SearchEngineService)

Client and server  located on different ports and communicate over a network on a particular numerical port.   

Application works with small documents where each document contains a series of tokens (words).  

The usage model of application:
* Put documents into the search engine and get identification key;
* Get document by identification key;
* Search on a string of tokens to return identification  keys of all documents that contain all tokens in the set;

## Technical settings:
-  Java 8
-  Spring Boot  1.5.9
-  Hibernate
-  Maven
-  H2 (test db)

Document requirements:
- max-file-size: 1000KB
- max-request-size: 1000KB

## Run application:
- 1 Start SearchEngineService (read README.md for this project)
- 2 Start  SearchEnginePortal (read README.md for this project)