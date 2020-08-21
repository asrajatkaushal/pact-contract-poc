# pact-contract-poc
POC for evaluating contract testing with pact.io

Setup
-----

#### Steps
* Run `docker-compose up` from within pact-broker
* Run `mvn clean install` in both pact-consumer & pact-producer.
* Run tests specified in `GetTransactionsTest` in pact-consumer.
* Run `mvn pact:publish` in pact-consumer
* Run pact-producer application
* Run tests specified in `GetTransactionsContractVerificationTest` in pact-producer.

> **Note:** Please create directories data\pgdata inside pact-broker\db