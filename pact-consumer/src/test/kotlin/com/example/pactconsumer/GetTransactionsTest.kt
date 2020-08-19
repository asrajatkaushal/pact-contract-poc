package com.example.pactconsumer

import au.com.dius.pact.consumer.*
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.consumer.model.MockProviderConfig
import au.com.dius.pact.core.model.RequestResponsePact
import com.example.producer.models.Transaction
import io.pactfoundation.consumer.dsl.LambdaDsl.newJsonBody
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate


@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "transactionsProducer", port = "9999")
class GetTransactionsTest {

    @Test
    @PactTestFor(pactMethod = "testPact")
    fun testPact() {
        val pact = getPactUsingJson()
//        val pact = getPactUsingPactDSL()
//        val pact = getPactUsingLambdaDSL()
        val config = MockProviderConfig.createDefault();

        val pactTestRun = object : PactTestRun<Transaction?> {
            @Throws(Exception::class)
            override fun run(mockServer: MockServer, context: PactTestExecutionContext?): Transaction? {
                val restTemplate = RestTemplate()

                val transactionResponseEntity: ResponseEntity<Transaction> = restTemplate
                        .getForEntity("${mockServer.getUrl()}/transactions", Transaction::class.java)

                assertEquals(transactionResponseEntity.statusCodeValue, 200)
                assertEquals(transactionResponseEntity.body?.transactionId, 999999999)
                assertEquals(transactionResponseEntity.body?.referenceNo, "118866227733")

                return transactionResponseEntity.body
            }
        }

        val result: PactVerificationResult = runConsumerTest(pact, config, pactTestRun);

        if (result is PactVerificationResult.Error)
            throw RuntimeException(result.error)

        assert(result is PactVerificationResult.Ok)
    }

    fun getPactUsingJson(): RequestResponsePact {
        val headers: MutableMap<String, String> = mutableMapOf()
        headers["content-type"] = "application/json"

        return ConsumerPactBuilder
                .consumer("transactionsConsumer")
                .hasPactWith("transactionsProvider")
                .uponReceiving("get transactions request")
                .path("/transactions")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("{\"transactionId\":999999999,\"referenceNo\":\"118866227733\",\"initiatingCompany\":{\"name\":\"Stark Industries\",\"customerNumber\":\"10002\"},\"beneficiary\":{\"name\":\"Clark Kent\",\"bankName\":\"BANQUE ERIC STURDZA S.A.\",\"bankCountryCode\":\"CHE\",\"bankCountryName\":\"Switzerland\",\"accountNumber\":\"CK741852\",\"email\":\"clark.kent@wayneenterprises.com\",\"address\":\"Dark Knight Cave\",\"swiftCode\":\"CHEMMUPLXXX\",\"iban\":\"ES7254874628585487945019\",\"isInactive\":false},\"sameDayValue\":\"SAME_DAY_VALUE\",\"amount\":25.857142857142858,\"amountMur\":1035.5785714285714,\"currency\":\"USD\",\"status\":[\"TREASURY_MONEY_MARKET\"],\"processingStatus\":[\"RM_CHECK_WITH_CUSTOMER_TO_PROCEED\"],\"dealTicketNumber\":\"\",\"fromAccount\":{\"number\":\"000409453724\",\"currency\":\"ZAR\",\"balance\":5007001.47,\"balanceMur\":246913578,\"availableBalance\":5007001.47,\"availableBalanceMur\":246913578},\"ftReference\":\"FT85269OK756\",\"dateTimeReceived\":1597779001258,\"dateTimeReceivedOnT24\":1597346987000,\"purposeDescription\":\"Air transport - Freight charges\",\"intervenors\":[{\"auditId\":17,\"dateTime\":1597692587000,\"userId\":\"K100000009\",\"buCode\":\"BU503635XX96969\",\"buName\":\"Credit Executive\",\"action\":\"Recommend\"}],\"comments\":[],\"documents\":[],\"relationshipManager\":{\"id\":\"BATMAN\",\"name\":\"Bruce Wayne\"},\"alternateRelationshipManager\":{\"id\":\"K10078\",\"name\":\"Jacqueline Simpson\"},\"tranType\":\"SIC\",\"transferOption\":\"STANDARD\"}")
                .toPact()
    }

    fun getPactUsingPactDSL(): RequestResponsePact {
        val headers: MutableMap<String, String> = mutableMapOf()
        headers["content-type"] = "application/json"

        return ConsumerPactBuilder
                .consumer("transactionsConsumer")
                .hasPactWith("transactionsProvider")
                .uponReceiving("get transactions request")
                .path("/transactions")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(PactDslJsonBody()
                        .numberValue("transactionId", 999999999)
                        .stringValue("referenceNo", "118866227733")
                        .stringValue("sameDayValue", "SAME_DAY_VALUE")
                        .numberValue("amount", 25.857142857142858)
                        .numberValue("amountMur", 1035.5785714285714)
                        .stringValue("currency", "USD")
                        .stringValue("dealTicketNumber", "")
                        .stringValue("ftReference", "FT85269OK756")
                        .numberValue("dateTimeReceived", 1597779001258)
                        .numberValue("dateTimeReceivedOnT24", 1597346987000)
                        .stringValue("purposeDescription", "Air transport - Freight charges")
                        .stringValue("tranType", "SIC")
                        .stringValue("transferOption", "STANDARD")
                        .`object`("initiatingCompany")
                        .stringValue("name", "Stark Industries")
                        .stringValue("customerNumber", "10002")
                        .closeObject()
                        .`object`("beneficiary")
                        .stringValue("name", "Clark Kent")
                        .stringValue("bankName", "BANQUE ERIC STURDZA S.A.")
                        .stringValue("bankCountryCode", "CHE")
                        .stringValue("bankCountryName", "Switzerland")
                        .stringValue("accountNumber", "CK741852")
                        .stringValue("email", "clark.kent@wayneenterprises.com")
                        .stringValue("address", "Dark Knight Cave")
                        .stringValue("swiftCode", "CHEMMUPLXXX")
                        .stringValue("iban", "ES7254874628585487945019")
                        .booleanValue("isInactive", false)
                        .closeObject()
                        .`object`("fromAccount")
                        .stringValue("number", "000409453724")
                        .stringValue("currency", "ZAR")
                        .numberValue("balance", 5007001.47)
                        .numberValue("balanceMur", 246913578)
                        .numberValue("availableBalance", 5007001.47)
                        .numberValue("availableBalanceMur", 246913578)
                        .closeObject()
                        .`object`("relationshipManager")
                        .stringValue("id", "BATMAN")
                        .stringValue("name", "Bruce Wayne")
                        .closeObject()
                        .`object`("alternateRelationshipManager")
                        .stringValue("id", "K10078")
                        .stringValue("name", "Jacqueline Simpson")
                        .closeObject()
                        .array("status")
                        .stringValue("TREASURY_MONEY_MARKET")
                        .closeArray()
                        .array("processingStatus")
                        .stringValue("RM_CHECK_WITH_CUSTOMER_TO_PROCEED")
                        .closeArray()
                        .array("intervenors")
                        .`object`()
                        .numberValue("auditId", 17)
                        .numberValue("dateTime", 1597692587000)
                        .stringValue("userId", "K100000009")
                        .stringValue("buCode", "BU503635XX96969")
                        .stringValue("buName", "Credit Executive")
                        .stringValue("action", "Recommend")
                        .closeObject()
                        .closeArray()
                        .array("comments")
                        .closeArray()
                        .array("documents")
                        .closeArray()
                ).toPact()
    }

    fun getPactUsingLambdaDSL(): RequestResponsePact {
        val headers: MutableMap<String, String> = mutableMapOf()
        headers["content-type"] = "application/json"

        return ConsumerPactBuilder
                .consumer("transactionsConsumer")
                .hasPactWith("transactionsProvider")
                .uponReceiving("get transactions request")
                .path("/transactions")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(newJsonBody { trans ->
                    trans.numberType("transactionId", 999999999)
                    trans.stringType("referenceNo", "118866227733")
                    trans.stringType("sameDayValue", "SAME_DAY_VALUE")
                    trans.numberType("amount", 25.857142857142858)
                    trans.numberType("amountMur", 1035.5785714285714)
                    trans.stringType("currency", "USD")
                    trans.stringType("dealTicketNumber", "")
                    trans.stringType("ftReference", "FT85269OK756")
                    trans.numberType("dateTimeReceived", 1597779001258)
                    trans.numberType("dateTimeReceivedOnT24", 1597346987000)
                    trans.stringType("purposeDescription", "Air transport - Freight charges")
                    trans.stringType("tranType", "SIC")
                    trans.stringType("transferOption", "STANDARD")
                    trans.`object`("initiatingCompany") {
                        it.stringType("name", "Stark Industries")
                        it.stringType("customerNumber", "10002")
                    }
                    trans.`object`("beneficiary") {
                        it.stringType("name", "Clark Kent")
                        it.stringType("bankName", "BANQUE ERIC STURDZA S.A.")
                        it.stringType("bankCountryCode", "CHE")
                        it.stringType("bankCountryName", "Switzerland")
                        it.stringType("accountNumber", "CK741852")
                        it.stringType("email", "clark.kent@wayneenterprises.com")
                        it.stringType("address", "Dark Knight Cave")
                        it.stringType("swiftCode", "CHEMMUPLXXX")
                        it.stringType("iban", "ES7254874628585487945019")
                        it.booleanType("isInactive", false)
                    }
                    trans.`object`("fromAccount") {
                        it.stringType("number", "000409453724")
                        it.stringType("currency", "ZAR")
                        it.numberType("balance", 5007001.47)
                        it.numberType("balanceMur", 246913578)
                        it.numberType("availableBalance", 5007001.47)
                        it.numberType("availableBalanceMur", 246913578)
                    }
                    trans.`object`("relationshipManager") {
                        it.stringType("id", "BATMAN")
                        it.stringType("name", "Bruce Wayne")
                    }
                    trans.`object`("alternateRelationshipManager") {
                        it.stringType("id", "K10078")
                        it.stringType("name", "Jacqueline Simpson")
                    }
                    trans.array("status") {
                        it.stringValue("TREASURY_MONEY_MARKET")
                    }
                    trans.array("processingStatus") {
                        it.stringValue("RM_CHECK_WITH_CUSTOMER_TO_PROCEED")
                    }
                    trans.array("intervenors") { i ->
                        i.`object` {
                            it.numberType("auditId", 17)
                            it.numberType("dateTime", 1597692587000)
                            it.stringType("userId", "K100000009")
                            it.stringType("buCode", "BU503635XX96969")
                            it.stringType("buName", "Credit Executive")
                            it.stringType("action", "Recommend")
                        }
                    }
                    trans.array("comments") {}
                    trans.array("documents") {}
                }.build())
                .toPact()
    }

}