package com.example.pactproducer

import au.com.dius.pact.provider.junit.Consumer
import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.loader.PactFolder
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith


@Consumer("transactionsConsumer")
@Provider("transactionsProvider")
@PactFolder("pacts")
class GetTransactionsContractVerificationTest {

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }

}