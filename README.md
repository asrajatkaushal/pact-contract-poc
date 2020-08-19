# pact-contract-poc
POC for evaluating contract testing with pact.io

Setup
-----

#### Steps
* Run `docker-compose up` from within pact-broker
* Run `mvn clean install` in both pact-consumer & pact-producer.
* Run pact-consumer tests (`mvn clean test`)
* Run `mvn pact:publish` in pact-consumer
* Run pact-producer application
* Run pact-producer tests (`mvn clean test`)

> **Note:** Please create directories data\pgdata inside pact-broker\db