package com.example.pactproducer.controllers

import com.example.producer.models.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
class TransactionsController {

    @GetMapping("/transactions")
    fun getTransactions(): ResponseEntity<Transaction> {
        return ResponseEntity(
                Transaction(
                        transactionId = 999999999,
                        referenceNo = "118866227733",
                        initiatingCompany = Customer (
                                name = "Stark Industries",
                                customerNumber = "10002"
                        ),
                        beneficiary = Beneficiary (
                                name = "Clark Kent",
                                bankName = "BANQUE ERIC STURDZA S.A.",
                                bankCountryCode = "CHE",
                                bankCountryName = "Switzerland",
                                accountNumber = "CK741852",
                                email = "clark.kent@wayneenterprises.com",
                                address = "Dark Knight Cave",
                                swiftCode = "CHEMMUPLXXX",
                                iban = "ES7254874628585487945019",
                                isInactive = false
                        ),
                        sameDayValue = TreasuryValueDay.SAME_DAY_VALUE,
                        amount = 25.857142857142858,
                        amountMur = 1035.5785714285714,
                        currency = "USD",
                        status = listOf(StatusEnum.TREASURY_MONEY_MARKET),
                        processingStatus = listOf(ProcessingStatus.RM_CHECK_WITH_CUSTOMER_TO_PROCEED),
                        dealTicketNumber = "",
                        fromAccount = Account (
                                number = "000409453724",
                                currency = "ZAR",
                                balance = 5007001.47,
                                balanceMur = 246913578.0,
                                availableBalance = 5007001.47,
                                availableBalanceMur = 246913578.0
                        ),
                        ftReference = "FT85269OK756",
                        dateTimeReceived = 1597779001258,
                        dateTimeReceivedOnT24 = 1597346987000,
                        purposeDescription = "Air transport - Freight charges",
                        intervenors = listOf(AuditLog(
                                auditId = 17,
                                dateTime = 1597692587000,
                                userId = "K100000009",
                                buCode = "BU503635XX96969",
                                buName = "Credit Executive",
                                action = "Recommend"
                        )),
                        comments = emptyList(),
                        documents = emptyList(),
                        relationshipManager = User (
                                id = "BATMAN",
                                name = "Bruce Wayne"
                        ),
                        alternateRelationshipManager = User (
                                id = "K10078",
                                name = "Jacqueline Simpson"
                        ),
                        tranType = TransactionTypeEnum.SIC,
                        transferOption = TransferOptionEnum.STANDARD

                ), HttpStatus.OK)
    }
}
