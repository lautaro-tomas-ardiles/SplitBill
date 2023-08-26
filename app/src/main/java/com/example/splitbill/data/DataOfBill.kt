package com.example.splitbill.data

data class DataOfBill(
    var title: String,
    var description: String,
    var badge: String,
    val participants: List<String>,
    var id: Int
    )
