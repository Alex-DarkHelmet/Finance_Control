package com.alex_bystrov.safemoney.domain.common

import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel

data class DailyTotalModel(
    val date: String,
    val transactions: List<UserTransactionModel> = emptyList(),
    val totalDailyIncome: String,
    val totalDailyExpense: String,
)
