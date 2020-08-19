package com.example.producer.models

import java.util.*

data class Document(
        val documentId: Int,
        val document: String,
        val documentDateUploaded: Number,
        val userId: String
)