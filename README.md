# SearchEnginePortal
part of SearchEngine;

[Read](https://github.com/MaximDzhezhelo/SearchEnginePortal/blob/master/GENERAL.md) more information about SearchEngine;

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Prerequisites

What things you need to install the software and how to install them
* Java 8
* Maven

## Installing
    mvn clean install
    
## Running the tests
    mvn clean test
    
## Run application
    cd ~~/path/to/~~search-engine-portal-war 
    mvn clean spring-boot:run
    
## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## General info
Subsystem SearchEnginePortal - created for send request - handle response - answer for users information about document with token.

- **search-engine-portal-war**  - module for integration with SearchEngineService; 

## Generall settings:
* urn  - `/api/search-engine-portal`;
* port - `8081`;