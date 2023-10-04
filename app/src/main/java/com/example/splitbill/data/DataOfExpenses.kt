package com.example.splitbill.data

data class DataOfExpenses(
    val title: String,
    val amount: Double,
    val paidBy: String,
    val isTransfer: Boolean,
    var id: Int
    )
