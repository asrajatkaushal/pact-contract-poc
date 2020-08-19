package com.example.producer.models

import java.util.*

data class Comment(
        val commentId: Int,
        val userId: String,
        val userRole: RolesEnum,
        val commentTime: Number,
        val text: String
)