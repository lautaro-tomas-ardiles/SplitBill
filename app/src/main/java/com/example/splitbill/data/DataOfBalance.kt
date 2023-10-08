package com.example.splitbill.data

data class DataOfBalance(
    var amount: Double,
    var who: String,
    var isDebt: Boolean,
    var id: Int
)
