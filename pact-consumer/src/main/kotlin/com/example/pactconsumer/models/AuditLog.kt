package com.example.producer.models

import java.util.*

data class AuditLog(
        val auditId: Int,
        val dateTime: Number,
        val userId: String,
        val buCode: String,
        val buName: String,
        val action: String
)