package com.example.producer.models

data class Beneficiary(
        val name: String,
        val bankName: String,
        val bankCountryCode: String,
        val bankCountryName: String,
        val accountNumber: String,
        val email: String,
        val address: String,
        val swiftCode: String,
        val iban: String,
        val isInactive: Boolean
)