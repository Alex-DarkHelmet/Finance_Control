package com.alex_bystrov.safemoney.domain.features.transactions.models

data class DailyTransactionsModel(
    val date: String,
    val transactions: List<UserTransactionModel> = emptyList(),
    val totalIncomeByDay: Double = 0.0,
    val totalExpenseByDay: Double = 0.0
)
