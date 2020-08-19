package com.example.producer.models

import java.util.*

data class Transaction(
        val transactionId: Int,
        val referenceNo: String,
        val initiatingCompany: Customer,
        val beneficiary: Beneficiary,
        val sameDayValue: TreasuryValueDay,
        val amount: Double,
        val amountMur: Double,
        val currency: String,
        val status: List<StatusEnum>,
        val processingStatus: List<ProcessingStatus>,
        val dealTicketNumber: String,
        val fromAccount: Account,
        val ftReference: String,
        val dateTimeReceived: Number,
        val dateTimeReceivedOnT24: Number,
        val purposeDescription: String,
        val intervenors: List<AuditLog>,
        val comments: List<Comment>,
        val documents: List<Document>,
        val relationshipManager: User,
        val alternateRelationshipManager: User,
        val tranType: TransactionTypeEnum,
        val transferOption: TransferOptionEnum?
) {
//    fun toTransactionDTO(): TransactionDTO {
//        return TransactionDTO(
//                transactionId = transactionId,
//                referenceNo = ibReference,
//                dayValue = sameDayValue.toEnumDTO(),
//                account = fromAccount,
//                ftReference = ftReference,
//                amountMur = amountMur,
//                amount = amount,
//                currency = currency,
//                option = transferOption?.code ?: "",
//                beneficiary = beneficiary,
//                company = initiatingCompany,
//                processingStatus = processingStatus.map { t -> t.toEnumDTO() },
//                type = tranType.toTransactionType(),
//                dateTimeReceived = dateTimeReceived,
//                dateTimeReceivedOnT24 = dateTimeReceivedOnT24,
//                daysLeft = 0, //TODO add the logic to calculate remaining days!
//                ticketNumber = dealTicketNumber,
//                purpose = purposeDescription,
//                intervenors = intervenors,
//                alternateRM = alternateRelationshipManager,
//                relationshipManager = relationshipManager,
//                comments = comments,
//                status = status.map { t -> t.toEnumDTO() },
//                documents = documents
//        )
//    }
}

data class TransactionActions(
        val action: UserActions,
        val comment: String
)

data class TransactionDTO(
        val transactionId: Int,
        val referenceNo: String,
        val dayValue: EnumDTO,
        val account: Account,
        val ftReference: String,
        val amount: Double,
        val amountMur: Double,
        val currency: String,
        val option: String,
        val beneficiary: Beneficiary,
        val company: Customer,
        val processingStatus: List<EnumDTO>,
        val type: TransactionType,
        val dateTimeReceived: Number,
        val dateTimeReceivedOnT24: Number,
        val daysLeft: Int,
        val ticketNumber: String,
        val purpose: String,
        val intervenors: List<AuditLog>,
        val alternateRM: User ,
        val relationshipManager: User,
        val comments: List<Comment>,
        val status: List<EnumDTO>,
        val documents: List<Document>
)


data class TransactionType(
        val id: String,
        val label: String,
        val orderNumber: Int,
        val groupId: String,
        val transferMode: String
)
