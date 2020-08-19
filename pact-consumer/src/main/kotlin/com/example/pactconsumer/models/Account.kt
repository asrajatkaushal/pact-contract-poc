package com.example.producer.models

data class Account(
        val number: String,
        val currency: String,
        val balance: Double,
        val balanceMur: Double,
        val availableBalance: Double,
        val availableBalanceMur: Double
)