package com.example.producer.models

enum class StatusEnum(val code: String, val label: String, val orderNumber: Int) {
    HIGH_RISK("HIGH_RISK", "High risk transaction", 1),
    AFRICAN_DESK("AFRICAN_DESK", "African desk marker", 2),
    POSTING_RESTRICTED("POSTING_RESTRICTED", "Posting restriction", 3),
    TREASURY_MONEY_MARKET("TREASURY_MONEY_MARKET", "Awaiting Treasury Money Market approval", 4),
    AMA_CE("AMA_CE", "Awaiting AMA/CE approval", 5),
    DEAL_TICKET("DEAL_TICKET", "Awaiting deal ticket from Treasury Sales", 6),
    POBU("POBU", "Awaiting POBU", 7),
    INSUFFICIENT_FUNDS("INSUFFICIENT_FUNDS", "Insufficient funds", 8),
    UNKNOWN("UNKNOWN", "", 0);

    fun toEnumDTO(): EnumDTO {
        return EnumDTO(code, code, label, orderNumber)
    }

    companion object {
        fun fromText(code: String) = values().firstOrNull { r -> r.code == code }
                ?: UNKNOWN
    }
}

enum class ProcessingStatus(val statusCode: String, val label: String, val orderNumber: Int) {
    REQUEST_AE_AUTHORIZATION("REQUEST_AE_AUTHORIZATION", "Request Ae Authorization", 1),
    RM_CHECK_WITH_CUSTOMER_TO_PROCEED("RM_CHECK_WITH_CUSTOMER_TO_PROCEED", "Check with customer", 2),
    RM_JUSTIFICATION("RM_JUSTIFICATION", "TO justify", 3),
    UNKNOWN("UNKNOWN", "", 0);

    fun toEnumDTO(): EnumDTO {
        return EnumDTO(statusCode, statusCode, label, orderNumber)
    }

    companion object {
        fun fromText(status: String) = values().firstOrNull { r -> r.statusCode == status }
                ?: UNKNOWN
    }
}

enum class UserActions {
    APPROVE,
    CANCEL
}

enum class Actions {
    TO_RECOMMEND,
    IN_PROGRESS,
    TO_APPROVE,
    NONE
}

enum class TreasuryValueDay(val value: Int, val code: String, val label: String, val orderNumber: Int) {
    SAME_DAY_VALUE(value = 0, code = "SAME_DAY_VALUE", label = "Same day value", orderNumber = 1),
    ONE_DAY_VALUE(value = 1, code = "ONE_DAY_VALUE", label = "One day value", orderNumber = 2),
    TWO_DAY_VALUE(value = 2, code = "TWO_DAY_VALUE", label = "Two day value", orderNumber = 3),
    EXPIRED(value = -1, code = "EXPIRED", label = "Expired", orderNumber = 0);

    fun toEnumDTO(): EnumDTO {
        return EnumDTO(code, code, label, orderNumber)
    }

    companion object {
        fun fromInt(value: Int) = values().firstOrNull { v -> v.value == value }
                ?: EXPIRED
    }
}

enum class RolesEnum(val roleCode: String, val jwtRoleCode: String = "ROLE_${roleCode.toUpperCase()}", val roleDescription: String) {
    BACKOFFICE(roleCode = "BACKOFFICE", roleDescription = "BackOffice"),
    RELATIONSHIP_MANAGER(roleCode = "RM", roleDescription = "Relationship Manager"),
    UNKNOWN_ROLES(roleCode = "NOROLE", roleDescription = "");

    companion object {
        fun fromJWT(role: String) = values().firstOrNull { r -> r.jwtRoleCode == role }
                ?: UNKNOWN_ROLES

        fun fromText(role: String) = values().firstOrNull { r -> r.roleCode == role }
                ?: UNKNOWN_ROLES
    }
}

enum class LoginStatus(val statusInt: Int, val errorMessage: List<String>, val errorHeader: String, val popupType: Int) {
    SUCCESSFULL(0, emptyList(), "", 0), //Successful login, credential are ok and user is in required group
    INVALID_CREDENTIALS(30, listOf("Invalid credentials.", "Please try again."), "", 1), // Windows credential not ok
    UNAUTHORISED_ACCESS(40, listOf("Sorry, but you don’t have permission to access this application. Please contact the bank’s administrator."), "Unauthorised access", 2) // User does not belongs to required group
}

enum class TransactionTypeEnum constructor(val id: String, val label: String, val orderNumber: Int, var groupId: String = id, val transferMode: String) {
    T24("T24", "Bulk payment", 0, "T24", ""),
    CCP("CCP", "Credit card", 1, "CCP", "Own"),
    CPN("CPN", "Credit card", 1, "CCP", "Third party"),
    DDF("DDF", "Direct debit", 3, "DDF", ""),
    ITF("ITF", "Foreign currency", 4, "ITF", ""),
    DTF("DTF", "Local bank", 5, "DTF", "D"),
    ITG("ITG", "MCB account", 6, "ITG", "I"),
    OAT("OAT", "Own account", 7, "OAT", "O"),
    SIA("SIA", "Standing order amendment", 8, "SIA", ""),
    SIC("SIC", "Standing order cancellation", 9, "SIC", ""),
    SIS("SIS", "Standing order setup", 10, "SIS", ""),
    UNKNOWN("UNK", "Unknow", -1, "UNK", "");

    fun toTransactionType(): TransactionType {
        return TransactionType(id, label, orderNumber, groupId, transferMode)
    }

    companion object {
        private val map = TransactionTypeEnum.values().associateBy(TransactionTypeEnum::id)
        private fun isExist(code: String) = map[code] != null

        fun fromText(value: String): TransactionTypeEnum = if (isExist(value)) map.getValue(value) else UNKNOWN

    }
}

enum class TransferOptionEnum constructor(val code: String, val label: String) {
    EXPRESS("E", "Express"),
    STANDARD("S", "Standard");

    companion object {
        private val map = values().associateBy(TransferOptionEnum::code)

        private fun isExist(code: String) = map[code] != null
        fun getTransferOption(code: String?): TransferOptionEnum? = if (!code.isNullOrBlank() && isExist(code)) map.getValue(code) else null
    }
}


data class EnumDTO(
        val id: String,
        val value: String,
        val label: String,
        val orderNumber: Int
)

enum class adAccountStatus {
    ACCOUNT_DISABLED,
    ACCOUNT_LOCKED,
    ACCOUNT_OK,
    WRONG_CREDENTIALS
}