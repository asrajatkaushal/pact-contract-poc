package com.example.pactproducer

import au.com.dius.pact.provider.junit.Consumer
import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.loader.PactBroker
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith


@Consumer("transactionsConsumer")
@Provider("transactionsProvider")
//@PactFolder("pacts")
// we can even provide pact_broker as the host name as defined in docker-compose
// but then we need to map port 80 to port 80 & even update host & remove port from everywhere
@PactBroker(host = "localhost", port = "9090")
class GetTransactionsContractVerificationTest {

    companion object {
        @BeforeAll
        @JvmStatic
        internal fun enablePublishingPact() {
            System.setProperty("pact.verifier.publishResults", "true")
        }
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }

}